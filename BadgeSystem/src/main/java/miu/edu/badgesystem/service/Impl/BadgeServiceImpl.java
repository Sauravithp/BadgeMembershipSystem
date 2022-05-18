package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.response.BadgeResponseDTO;
import miu.edu.badgesystem.repository.BadgeRepository;
import miu.edu.badgesystem.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeServiceImpl implements BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    @Override
    public BadgeResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public List<BadgeResponseDTO> findAll() {
        return null;
    }

    @Override
    public void deleteBadge(Long badgeId) {

    }
}
