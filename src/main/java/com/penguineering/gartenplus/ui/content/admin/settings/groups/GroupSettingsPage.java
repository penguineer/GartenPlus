package com.penguineering.gartenplus.ui.content.admin.settings.groups;

import com.penguineering.gartenplus.auth.group.GroupDTO;
import com.penguineering.gartenplus.auth.group.GroupEntity;
import com.penguineering.gartenplus.auth.group.GroupRepository;
import com.penguineering.gartenplus.ui.appframe.GartenplusPage;
import com.penguineering.gartenplus.ui.content.admin.settings.SettingsLayout;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Route(value = "groups", layout = SettingsLayout.class)
@RolesAllowed("ADMINISTRATOR")
@PageTitle("GartenPlus | Einstellungen | Gruppen")
public class GroupSettingsPage extends GartenplusPage {
    private final GroupRepository groupRepository;

    private final H3 editorHeadline;
    private final GroupEditor editor;
    private final GroupsGrid groupsGrid;

    public GroupSettingsPage(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;


        editorHeadline = new H3();
        add(editorHeadline);

        editor = new GroupEditor(this::saveGroup,
                () -> editGroup(null),
                this::verifyName);
        add(editor);

        add(new H3("Gruppen"));

        groupsGrid = new GroupsGrid(this::editGroup, this::confirmDeleteGroup);
        add(groupsGrid);

        loadData();
        editGroup(null);
    }

    private void loadData() {
        var groups =
                StreamSupport.stream(groupRepository.findAll().spliterator(), false)
                        .map(GroupEntity::toDTO)
                        .toList();
        groupsGrid.setItems(groups);
    }

    private boolean verifyName(String name, UUID current) {
        return Optional.ofNullable(name)
                .flatMap(groupRepository::findByName)
                .filter(g -> !Objects.equals(g.getId(), current))
                .isEmpty();
    }

    private void saveGroup(GroupDTO groupDTO) {
        Mono.just(groupDTO)
                .mapNotNull(GroupDTO::id)
                .map(groupRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .switchIfEmpty(Mono.just(new GroupEntity()))
                .doOnNext(groupEntity -> {
                    groupEntity.setName(groupDTO.name());
                    groupEntity.setDescription(groupDTO.description());
                })
                .map(groupRepository::save)
                .map(GroupEntity::toDTO)
                .doOnNext(editor::setGroup)
                .blockOptional()
                // open a new editor if the group was stored successfully
                .ifPresent(g -> editGroup(null));

        loadData();
    }

    private void editGroup(UUID id) {
        Mono.justOrEmpty(id)
                .map(groupRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(GroupEntity::toDTO)
                .doOnNext(editor::setGroup)
                .blockOptional()
                .ifPresentOrElse(
                        group -> editorHeadline.setText("Gruppe bearbeiten"),
                        () -> {
                            editorHeadline.setText("Gruppe erstellen");
                            editor.setGroup(null);
                        });
    }

    private void confirmDeleteGroup(UUID id) {
        Mono.just(id)
                .map(groupRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(this::createDeleteConfirmDialog)
                .blockOptional()
                .ifPresent(ConfirmDialog::open);
    }

    private ConfirmDialog createDeleteConfirmDialog(GroupEntity group) {
        return new ConfirmDialog("Gruppe " + group.getName() + " löschen",
                "Soll die Gruppe " + group.getName() + " wirklich gelöscht werden?",
                "Gruppe löschen",
                confirmed -> deleteGroup(group),
                "Abbrechen",
                cancel -> {
                });
    }

    private void deleteGroup(GroupEntity group) {
        Mono.just(group)
                .doOnNext(groupRepository::delete)
                .block();

        loadData();
    }
}
