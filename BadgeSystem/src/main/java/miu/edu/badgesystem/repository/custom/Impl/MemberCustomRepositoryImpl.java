package miu.edu.badgesystem.repository.custom.Impl;

import miu.edu.badgesystem.model.Membership;
import miu.edu.badgesystem.repository.custom.MemberCustomRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

import static miu.edu.badgesystem.util.QueryUtil.*;

@Repository
@Transactional(readOnly = true)
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public Membership getMembershipByBadgeAndLocation(String badgeNumber, Long locationId) {
        Query query = createQuery.apply(entityManager, queryString)
                .setParameter("locationId",locationId)
                .setParameter("badgeNumber",badgeNumber);

        Membership results = transformQueryToSingleResult(query, Membership.class);

        return results;
    }

    private String queryString="SELECT   \n" +
            "ms.id as id,\n" +
            "ms.start_date as startDate,\n" +
            "ms.end_date as endDate,\n" +
            "ms.status as status,\n" +
            "ms.location_id as location,\n" +
            "ms.plan_role_id as planRoleInfo\n" +
            "from Badge b \n" +
            "JOIN `Member` m ON m.id=b.member_id \n" +
            "JOIN MembershipInfo mi ON mi.member_id=m.id \n" +
            "JOIN Membership ms ON ms.id=mi.membership_id \n" +
            "JOIN Location l ON l.id=ms.location_id \n" +
            "WHERE l.id=:locationID\n" +
            "AND b.badge_number=:badgeNumber ";
}
