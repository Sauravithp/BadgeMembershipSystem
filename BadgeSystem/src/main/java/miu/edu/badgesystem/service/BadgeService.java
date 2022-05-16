package miu.edu.badgesystem.service;


import miu.edu.badgesystem.dto.response.BadgeResponseDTO;

import java.util.List;

public interface BadgeService {


    BadgeResponseDTO findById(Long id);

    List<BadgeResponseDTO> findAll();

    void deleteBadge(Long badgeId);

}
