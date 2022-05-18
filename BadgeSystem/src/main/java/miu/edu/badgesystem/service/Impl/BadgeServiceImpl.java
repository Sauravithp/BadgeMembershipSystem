package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.response.BadgeResponseDTO;
import miu.edu.badgesystem.model.Badge;
import miu.edu.badgesystem.repository.BadgeRepository;
import miu.edu.badgesystem.repository.MemberRepository;
import miu.edu.badgesystem.service.BadgeService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BadgeServiceImpl implements BadgeService {

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    MemberRepository memberRepository;


    @Override
    public BadgeResponseDTO findById(Long id) {
        Badge badge = badgeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Badge with id " + id + " NOT FOUND"));
        return ModelMapperUtils.map(badge, BadgeResponseDTO.class);
    }

    @Override
    public List<BadgeResponseDTO> findAll() {
        List<Badge> badges = badgeRepository.findAll();
        return badges.stream().map(badge -> ModelMapperUtils.map(badge, BadgeResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBadge(Long badgeId) {
        Badge badge = badgeRepository.getById(badgeId);
        badge.setStatus('N');
        badgeRepository.save(badge);
    }


}
