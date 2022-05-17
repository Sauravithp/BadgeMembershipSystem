package miu.edu.badgesystem.repository.custom;

import miu.edu.badgesystem.model.Membership;


public interface MemberCustomRepository {

    Membership getMembershipByBadgeAndLocation(String badgeNumber,Long locationId);

}
