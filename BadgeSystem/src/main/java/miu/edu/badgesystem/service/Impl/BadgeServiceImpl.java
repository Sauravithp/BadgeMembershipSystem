package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.repository.BadgeRepository;
import miu.edu.badgesystem.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeServiceImpl implements BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;
}
