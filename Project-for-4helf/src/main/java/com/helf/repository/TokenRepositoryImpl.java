package com.helf.repository;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.helf.dto.TokenSearchCriteria;
import com.helf.entity.Token;
import com.helf.utils.CommonUtils;
import org.springframework.stereotype.Repository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class TokenRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Token> getTokenDetails(TokenSearchCriteria criteria) {
        Query query = null;
        boolean addClause = false;
        StringBuilder sb = new StringBuilder();
        sb.append("select t from Token t");
        if (Objects.nonNull(criteria)) {
            if (!CommonUtils.isEmptyString(criteria.getClinicName())) {
                addClauseIfRequired(addClause, sb);
                sb.append("upper(t.clinicName) = upper('" + criteria.getClinicName() + "')");
                addClause = true;
            }
            if (!CommonUtils.isEmptyString(criteria.getMobileNumber())) {
                addClauseIfRequired(addClause, sb);
                sb.append("upper(t.mobileNumber) = upper('" + criteria.getMobileNumber() + "')");
                addClause = true;
            }
            if (criteria.getStatus() != null) {
                addClauseIfRequired(addClause, sb);
                sb.append("t.status = '"+ criteria.getStatus().ordinal()+ "'");
                addClause = true;
            }
            if (criteria.getTokenType() != null) {
                addClauseIfRequired(addClause, sb);
                sb.append("t.tokenType = '"+ criteria.getTokenType().ordinal() + "'");
                addClause = true;
            }
            if (criteria.getFromDate() != null || criteria.getToDate() != null) {
                Long startTime = criteria.getFromDate() == null ? criteria.getToDate() : criteria.getFromDate();
                Long endTime = criteria.getToDate() == null ? criteria.getFromDate() : criteria.getToDate();
                long startOfDay = CommonUtils.getStartDate(startTime);
                LocalDate date = Instant.ofEpochMilli(endTime).atZone(ZoneId.systemDefault()).toLocalDate();
                long endOfDay = CommonUtils.getEndDay(date);
                addClauseIfRequired(addClause, sb);
                if (criteria.getToDate() != null) {
                    sb.append("t.modifiedTime >= " + startOfDay + " AND t.modifiedTime < " + endOfDay);
                } else {
                    sb.append("t.createdTime >= " + startOfDay + " AND t.createdTime < " + endOfDay);
                }
                addClause = true;
            }
            if (!CommonUtils.isEmptyString(criteria.getDoctorName())) {
                addClauseIfRequired(addClause, sb);
                sb.append("upper(t.doctorName) = upper('" + criteria.getDoctorName() + "')");
                addClause = true;
            }
            if (criteria.getTokenNumber()!=null) {
                addClauseIfRequired(addClause, sb);
                sb.append("t.tokenNumber =" + criteria.getTokenNumber());
                addClause = true;
            }

        }
        sb.append(" order by t.modifiedTime desc");
        query = entityManager.createQuery(sb.toString());
        query.setFirstResult(criteria.getOffset());
        query.setMaxResults(criteria.getLimit());
        log.info("getTokenDetails() successfully");
        return query.getResultList();
    }

    private void addClauseIfRequired(boolean values, StringBuilder sb) {
        if (values == false) {
            sb.append(" WHERE ");
        } else {
            sb.append(" AND ");
        }
    }
}

