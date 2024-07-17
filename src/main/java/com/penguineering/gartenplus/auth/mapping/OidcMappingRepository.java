package com.penguineering.gartenplus.auth.mapping;

import org.springframework.data.repository.CrudRepository;

public interface OidcMappingRepository extends CrudRepository<OidcMapping, OidcMappingKey> {
}
