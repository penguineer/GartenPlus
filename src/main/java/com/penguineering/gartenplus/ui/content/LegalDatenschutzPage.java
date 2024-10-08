package com.penguineering.gartenplus.ui.content;

import com.penguineering.gartenplus.ui.appframe.AppFrameLayout;
import com.penguineering.gartenplus.ui.appframe.GartenplusPage;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route(value = "/datenschutz", layout = AppFrameLayout.class)
@AnonymousAllowed
@PageTitle("GartenPlus | Datenschutz")
public class LegalDatenschutzPage extends GartenplusPage {
    public LegalDatenschutzPage() {
        add(new H2("Datenschutz"));

        add(new H3("Allgemeine Hinweise"));
        add(new Paragraph("""
                Die folgenden Hinweise geben einen einfachen Überblick darüber, was
                mit Ihren personenbezogenen Daten passiert, wenn Sie diese Website
                besuchen. Personenbezogene Daten sind alle Daten, mit denen Sie
                persönlich identifiziert werden können. Ausführliche Informationen zum
                Thema Datenschutz entnehmen Sie unserer unter diesem Text aufgeführten
                Datenschutzerklärung.
                """));

        add(new H4("Datenerfassung auf dieser Website"));
        add(new Paragraph("""
                Wer ist verantwortlich für die Datenerfassung auf dieser Website? Die
                Datenverarbeitung auf dieser Website erfolgt durch den Websitebetreiber.
                Dessen Kontaktdaten können Sie dem Abschnitt „Hinweis zur
                Verantwortlichen Stelle“ in dieser Datenschutzerklärung entnehmen.
                """));

        add(new H4("Wie erfassen wir Ihre Daten?"));
        add(new Paragraph("""
                Ihre Daten werden zum einen dadurch erhoben, dass Sie uns diese
                    mitteilen. Hierbei kann es sich z. B. um Daten handeln, die Sie in ein
                    Formular eingeben
                """));
        add(new Paragraph("""
                Andere Daten werden automatisch oder nach Ihrer Einwilligung beim
                    Besuch der Website durch unsere IT-Systeme erfasst. Das sind vor allem
                    technische Daten (z. B. Internetbrowser, Betriebssystem oder Uhrzeit des
                            Seitenaufrufs). Die Erfassung dieser Daten erfolgt automatisch, sobald
                    Sie diese Website betreten.
                """));
        add(new Paragraph("""
                Weiterhin werden Daten durch den OIDC-Provider erhoben, um die Anmeldung zu
                ermöglichen. Diese Daten werden nur in soweit in der Datenbank gespeichert,
                wie sie zur Durchführung des Betriebs notwendig sind.
                """));

        add(new H4("Wofür nutzen wir Ihre Daten?"));
        add(new Paragraph("""
                Ein Teil der Daten wird erhoben, um eine fehlerfreie Bereitstellung
                der Website zu gewährleisten. Andere Daten können zur Analyse Ihres
                Nutzerverhaltens verwendet werden.
                """));
        add(new Paragraph("""
                Über Formulare eingegebene Daten dienen der Umsetzung des Zwecks dieser Plattform und werden lediglich
                dafür gespeichert und verwendet.
                """));

        add(new H4("Welche Rechte haben Sie bezüglich Ihrer Daten?"));
        add(new Paragraph("""
                Sie haben jederzeit das Recht, unentgeltlich Auskunft über Herkunft,
                Empfänger und Zweck Ihrer gespeicherten personenbezogenen Daten zu
                erhalten. Sie haben außerdem ein Recht, die Berichtigung oder Löschung
                dieser Daten zu verlangen. Wenn Sie eine Einwilligung zur
                Datenverarbeitung erteilt haben, können Sie diese Einwilligung jederzeit
                für die Zukunft widerrufen. Außerdem haben Sie das Recht, unter
                bestimmten Umständen die Einschränkung der Verarbeitung Ihrer
                personenbezogenen Daten zu verlangen. Des Weiteren steht Ihnen ein
                Beschwerderecht bei der zuständigen Aufsichtsbehörde zu.
                """));
        add(new Paragraph("""
                Hierzu sowie zu weiteren Fragen zum Thema Datenschutz können Sie sich jederzeit an uns wenden.
                """));


        add(new H3("Allgemeine Hinweise und Pflicht\u00ADinformationen"));

        add(new H4("Datenschutz"));
        add(new Paragraph("""
                Die Betreiber dieser Seiten nehmen den Schutz Ihrer persönlichen
                Daten sehr ernst. Wir behandeln Ihre personenbezogenen Daten vertraulich
                und entsprechend den gesetzlichen Datenschutzvorschriften sowie dieser
                Datenschutzerklärung.
                """));
        add(new Paragraph("""
                Wenn Sie diese Website benutzen, werden verschiedene personenbezogene
                Daten erhoben. Personenbezogene Daten sind Daten, mit denen Sie
                persönlich identifiziert werden können. Die vorliegende
                Datenschutzerklärung erläutert, welche Daten wir erheben und wofür wir
                sie nutzen. Sie erläutert auch, wie und zu welchem Zweck das geschieht.
                """));
        add(new Paragraph("""
                Wir weisen darauf hin, dass die Datenübertragung im Internet (z. B.
                bei der Kommunikation per E-Mail) Sicherheitslücken aufweisen kann. Ein
                lückenloser Schutz der Daten vor dem Zugriff durch Dritte ist nicht
                möglich.
                """));

        add(new H4("Hinweis zur verantwortlichen Stelle"));
        add(new Paragraph("""
                Die verantwortliche Stelle für die Datenverarbeitung finden Sie im Impressum.
                """));
        add(new Paragraph("""
                Verantwortliche Stelle ist die natürliche oder juristische Person,
                die allein oder gemeinsam mit anderen über die Zwecke und Mittel der
                Verarbeitung von personenbezogenen Daten (z. B. Namen, E-Mail-Adressen
                o. Ä.) entscheidet.
                """));

        add(new H4("Speicherdauer"));
        add(new Paragraph("""
                Soweit innerhalb dieser Datenschutzerklärung keine speziellere
                Speicherdauer genannt wurde, verbleiben Ihre personenbezogenen Daten bei
                uns, bis der Zweck für die Datenverarbeitung entfällt. Wenn Sie ein
                berechtigtes Löschersuchen geltend machen oder eine Einwilligung zur
                Datenverarbeitung widerrufen, werden Ihre Daten gelöscht, sofern wir
                keine anderen rechtlich zulässigen Gründe für die Speicherung Ihrer
                personenbezogenen Daten haben (z. B. steuer- oder handelsrechtliche
                Aufbewahrungsfristen); im letztgenannten Fall erfolgt die Löschung nach
                Fortfall dieser Gründe.
                """));

        add(new H4("Allgemeine Hinweise zu den Rechtsgrundlagen der Datenverarbeitung auf dieser Website"));
        add(new Paragraph("""
                Sofern Sie in die Datenverarbeitung eingewilligt haben, verarbeiten
                wir Ihre personenbezogenen Daten auf Grundlage von Art. 6 Abs. 1 lit. a
                DSGVO bzw. Art. 9 Abs. 2 lit. a DSGVO, sofern besondere Datenkategorien
                nach Art. 9 Abs. 1 DSGVO verarbeitet werden. Im Falle einer
                ausdrücklichen Einwilligung in die Übertragung personenbezogener Daten
                in Drittstaaten erfolgt die Datenverarbeitung außerdem auf Grundlage von
                Art. 49 Abs. 1 lit. a DSGVO. Sofern Sie in die Speicherung von Cookies
                oder in den Zugriff auf Informationen in Ihr Endgerät (z. B. via
                Device-Fingerprinting) eingewilligt haben, erfolgt die Datenverarbeitung
                zusätzlich auf Grundlage von § 25 Abs. 1 TTDSG. Die Einwilligung ist
                jederzeit widerrufbar. Sind Ihre Daten zur Vertragserfüllung oder zur
                Durchführung vorvertraglicher Maßnahmen erforderlich, verarbeiten wir
                Ihre Daten auf Grundlage des Art. 6 Abs. 1 lit. b DSGVO. Des Weiteren
                verarbeiten wir Ihre Daten, sofern diese zur Erfüllung einer rechtlichen
                Verpflichtung erforderlich sind auf Grundlage von Art. 6 Abs. 1 lit. c
                DSGVO. Die Datenverarbeitung kann ferner auf Grundlage unseres
                berechtigten Interesses nach Art. 6 Abs. 1 lit. f DSGVO erfolgen. Über
                die jeweils im Einzelfall einschlägigen Rechtsgrundlagen wird in den
                folgenden Absätzen dieser Datenschutzerklärung informiert.
                """));

        add(new H4("Widerruf Ihrer Einwilligung zur Datenverarbeitung"));
        add(new Paragraph("""
                Viele Datenverarbeitungsvorgänge sind nur mit Ihrer ausdrücklichen
                Einwilligung möglich. Sie können eine bereits erteilte Einwilligung
                jederzeit widerrufen. Die Rechtmäßigkeit der bis zum Widerruf erfolgten
                Datenverarbeitung bleibt vom Widerruf unberührt.
                """));
        add(new Paragraph("""
                Widerspruchsrecht gegen die Datenerhebung in besonderen Fällen sowie
                gegen Direktwerbung (Art. 21 DSGVO) WENN DIE DATENVERARBEITUNG AUF
                GRUNDLAGE VON ART. 6 ABS. 1 LIT. E ODER F DSGVO ERFOLGT, HABEN SIE
                JEDERZEIT DAS RECHT, AUS GRÜNDEN, DIE SICH AUS IHRER BESONDEREN
                SITUATION ERGEBEN, GEGEN DIE VERARBEITUNG IHRER PERSONENBEZOGENEN DATEN
                WIDERSPRUCH EINZULEGEN; DIES GILT AUCH FÜR EIN AUF DIESE BESTIMMUNGEN
                GESTÜTZTES PROFILING. DIE JEWEILIGE RECHTSGRUNDLAGE, AUF DENEN EINE
                VERARBEITUNG BERUHT, ENTNEHMEN SIE DIESER DATENSCHUTZERKLÄRUNG. WENN SIE
                WIDERSPRUCH EINLEGEN, WERDEN WIR IHRE BETROFFENEN PERSONENBEZOGENEN
                DATEN NICHT MEHR VERARBEITEN, ES SEI DENN, WIR KÖNNEN ZWINGENDE
                SCHUTZWÜRDIGE GRÜNDE FÜR DIE VERARBEITUNG NACHWEISEN, DIE IHRE
                INTERESSEN, RECHTE UND FREIHEITEN ÜBERWIEGEN ODER DIE VERARBEITUNG DIENT
                DER GELTENDMACHUNG, AUSÜBUNG ODER VERTEIDIGUNG VON RECHTSANSPRÜCHEN
                (WIDERSPRUCH NACH ART. 21 ABS. 1 DSGVO).
                """));
        add(new Paragraph("""
                WERDEN IHRE PERSONENBEZOGENEN DATEN VERARBEITET, UM DIREKTWERBUNG ZU
                BETREIBEN, SO HABEN SIE DAS RECHT, JEDERZEIT WIDERSPRUCH GEGEN DIE
                VERARBEITUNG SIE BETREFFENDER PERSONENBEZOGENER DATEN ZUM ZWECKE
                DERARTIGER WERBUNG EINZULEGEN; DIES GILT AUCH FÜR DAS PROFILING, SOWEIT
                ES MIT SOLCHER DIREKTWERBUNG IN VERBINDUNG STEHT. WENN SIE
                WIDERSPRECHEN, WERDEN IHRE PERSONENBEZOGENEN DATEN ANSCHLIESSEND NICHT
                MEHR ZUM ZWECKE DER DIREKTWERBUNG VERWENDET (WIDERSPRUCH NACH ART. 21
                ABS. 2 DSGVO).
                """));

        add(new H4("Beschwerde\u00ADrecht bei der zuständigen Aufsichtsbehörde"));
        add(new Paragraph("""
                Im Falle von Verstößen gegen die DSGVO steht den Betroffenen ein
                Beschwerderecht bei einer Aufsichtsbehörde, insbesondere in dem
                Mitgliedstaat ihres gewöhnlichen Aufenthalts, ihres Arbeitsplatzes oder
                des Orts des mutmaßlichen Verstoßes zu. Das Beschwerderecht besteht
                unbeschadet anderweitiger verwaltungsrechtlicher oder gerichtlicher
                Rechtsbehelfe.
                """));

        add(new H4("Recht auf Datenübertragbarkeit"));
        add(new Paragraph("""
                Sie haben das Recht, Daten, die wir auf Grundlage Ihrer Einwilligung
                oder in Erfüllung eines Vertrags automatisiert verarbeiten, an sich oder
                an einen Dritten in einem gängigen, maschinenlesbaren Format aushändigen
                zu lassen. Sofern Sie die direkte Übertragung der Daten an einen anderen
                Verantwortlichen verlangen, erfolgt dies nur, soweit es technisch
                machbar ist.
                """));

        add(new H4("Auskunft, Löschung und Berichtigung"));
        add(new Paragraph("""
                Sie haben im Rahmen der geltenden gesetzlichen Bestimmungen jederzeit
                das Recht auf unentgeltliche Auskunft über Ihre gespeicherten
                personenbezogenen Daten, deren Herkunft und Empfänger und den Zweck der
                Datenverarbeitung und ggf. ein Recht auf Berichtigung oder Löschung
                dieser Daten. Hierzu sowie zu weiteren Fragen zum Thema personenbezogene
                Daten können Sie sich jederzeit an uns wenden.
                """));

        add(new H4("Recht auf Einschränkung der Verarbeitung"));
        add(new Paragraph("""
                Sie haben das Recht, die Einschränkung der Verarbeitung Ihrer
                personenbezogenen Daten zu verlangen. Hierzu können Sie sich jederzeit
                an uns wenden. Das Recht auf Einschränkung der Verarbeitung besteht in
                folgenden Fällen:
                """));
        add(new Paragraph("""
                Wenn Sie die Richtigkeit Ihrer bei uns gespeicherten
                personenbezogenen Daten bestreiten, benötigen wir in der Regel Zeit, um
                dies zu überprüfen. Für die Dauer der Prüfung haben Sie das Recht, die
                Einschränkung der Verarbeitung Ihrer personenbezogenen Daten zu
                verlangen. Wenn die Verarbeitung Ihrer personenbezogenen Daten
                unrechtmäßig geschah/geschieht, können Sie statt der Löschung die
                Einschränkung der Datenverarbeitung verlangen. Wenn wir Ihre
                personenbezogenen Daten nicht mehr benötigen, Sie sie jedoch zur
                Ausübung, Verteidigung oder Geltendmachung von Rechtsansprüchen
                benötigen, haben Sie das Recht, statt der Löschung die Einschränkung der
                Verarbeitung Ihrer personenbezogenen Daten zu verlangen. Wenn Sie einen
                Widerspruch nach Art. 21 Abs. 1 DSGVO eingelegt haben, muss eine
                Abwägung zwischen Ihren und unseren Interessen vorgenommen werden.
                Solange noch nicht feststeht, wessen Interessen überwiegen, haben Sie
                das Recht, die Einschränkung der Verarbeitung Ihrer personenbezogenen
                Daten zu verlangen. Wenn Sie die Verarbeitung Ihrer personenbezogenen
                Daten eingeschränkt haben, dürfen diese Daten – von ihrer Speicherung
                abgesehen – nur mit Ihrer Einwilligung oder zur Geltendmachung, Ausübung
                oder Verteidigung von Rechtsansprüchen oder zum Schutz der Rechte einer
                anderen natürlichen oder juristischen Person oder aus Gründen eines
                wichtigen öffentlichen Interesses der Europäischen Union oder eines
                Mitgliedstaats verarbeitet werden.
                """));


        add(new H4("Widerspruch gegen Werbe-E-Mails"));
        add(new Paragraph("""
                Der Nutzung von im Rahmen der Impressumspflicht veröffentlichten
                Kontaktdaten zur Übersendung von nicht ausdrücklich angeforderter
                Werbung und Informationsmaterialien wird hiermit widersprochen. Die
                Betreiber der Seiten behalten sich ausdrücklich rechtliche Schritte im
                Falle der unverlangten Zusendung von Werbeinformationen, etwa durch
                Spam-E-Mails, vor.
                """));


        add(new H3("Datenerfassung auf dieser Website"));

        add(new H4("Persönliche Daten"));
        add(new Paragraph("""
                Persönliche Daten werden im Rahmen des Profils so gespeichert, wie
                sie angegeben wurden. Von den OIDC-Providern bereitgestellte Daten
                werden lediglich zur korrekten Anzeige des eingeloggten Nutzers verwendet. Es wird
                lediglich die Account-ID des OIDC-Providers für eine Zuordnung der
                Anmeldung in der Datenbank hinterlegt.
                """));

        add(new H4("Server-Log-Dateien"));
        add(new Paragraph("""
                Die Erfassung der Log-Daten erfolgt auf Grundlage von Art. 6 Abs. 1
                lit. f DSGVO. Der Websitebetreiber hat ein berechtigtes Interesse an der
                technisch fehlerfreien Darstellung und der Optimierung seiner Website –
                hierzu müssen die Server-Log-
                Files erfasst werden.
                """));

        add(new H4("Anfrage per E-Mail, Telefon oder Telefax"));
        add(new Paragraph("""
                Wenn Sie uns per E-Mail, Telefon oder Telefax kontaktieren, wird Ihre
                Anfrage inklusive aller daraus hervorgehenden personenbezogenen Daten
                (Name, Anfrage) zum Zwecke der Bearbeitung Ihres Anliegens bei uns
                gespeichert und verarbeitet. Diese Daten geben wir nicht ohne Ihre
                Einwilligung weiter.
                """));
        add(new Paragraph("""
                Die Verarbeitung dieser Daten erfolgt auf Grundlage von Art. 6 Abs. 1
                lit. b DSGVO, sofern Ihre Anfrage mit der Erfüllung eines Vertrags
                zusammenhängt oder zur Durchführung vorvertraglicher Maßnahmen
                erforderlich ist. In allen übrigen Fällen beruht die Verarbeitung auf
                unserem berechtigten Interesse an der effektiven Bearbeitung der an uns
                gerichteten Anfragen (Art. 6 Abs. 1 lit. f DSGVO) oder auf Ihrer
                Einwilligung (Art. 6 Abs. 1 lit. a DSGVO) sofern diese abgefragt wurde;
                die Einwilligung ist jederzeit widerrufbar.
                """));
        add(new Paragraph("""
                Die von Ihnen an uns per Kontaktanfragen übersandten Daten verbleiben
                bei uns, bis Sie uns zur Löschung auffordern, Ihre Einwilligung zur
                Speicherung widerrufen oder der Zweck für die Datenspeicherung entfällt
                (z. B. nach abgeschlossener Bearbeitung Ihres Anliegens). Zwingende
                gesetzliche Bestimmungen – insbesondere gesetzliche Aufbewahrungsfristen
                – bleiben unberührt.
                """));
    }
}
