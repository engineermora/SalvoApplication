package com.salvo.SalvoApplication;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestResource
@RequestMapping("/rest/players/nn")
public interface PlayerRepository extends JpaRepository<Player, Long> {

}

