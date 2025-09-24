package com.datalogger.faults.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Component
public class FaultRepositoryImpl implements FaultRepository {

    private final int dataSourceIndex;
    
    @Autowired
    private ApplicationContext context;
    
    public FaultRepositoryImpl(int dataSourceIndex) {
        this.dataSourceIndex = dataSourceIndex;
    }
    
    private EntityManager getEntityManager() {
        try {
            @SuppressWarnings("unchecked")
            Map<String, LocalContainerEntityManagerFactoryBean> factories = 
                context.getBean("entityManagerFactories", Map.class);
            
            String key = "entityManagerFactory" + dataSourceIndex;
            LocalContainerEntityManagerFactoryBean factory = factories.get(key);
            
            if (factory == null) {
                throw new IllegalStateException("EntityManagerFactory not found for key: " + key);
            }
            
            EntityManagerFactory emf = factory.getObject();
            if (emf == null) {
                throw new IllegalStateException("EntityManagerFactory object is null for key: " + key);
            }
            
            return emf.createEntityManager();
        } catch (Exception e) {
            throw new IllegalStateException("Error getting EntityManager for index: " + dataSourceIndex, e);
        }
    }

    @Override
    public List<Object[]> allFaults() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            String sql = "SELECT " +
                     "r.dlStation, " +
                     "r.dlName, " +
                     "f.fltMsg, " +
                     "f.fltInfo, " +
                     "f.sysTime, " +
                     "f.sysYear, " +
                     "f.dlTime, " +
                     "f.faultLevel, " +
                     "f.faultType, " +
                     "f.createdTime, " +
                     "DATE '1970-01-01' + (((f.sysTime / 64) + 1735689600) / 86400) AS SYS_TIMESTAMP, " +
                     "DATE '1970-01-01' + (((f.dlTime / 64) + 1735689600) / 86400) AS DL_TIMESTAMP " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE f.faultPseudoType NOT IN ('VOLTAGE LOW', 'VOLTAGE HIGH','TEMPERATURE HIGH','TEMPERATURE LOW') " +
                     "ORDER BY f.sysTime DESC";
            
            Query query = em.createNativeQuery(sql);
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findNewFaultsSince(Timestamp lastCreatedTime) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            
            String sql = "SELECT " +
                     "r.dlStation, " +
                     "r.dlName, " +
                     "f.fltMsg, " +
                     "f.fltInfo, " +
                     "f.sysTime, " +
                     "f.sysYear, " +
                     "f.dlTime, " +
                     "f.faultLevel, " +
                     "f.faultType, " +
                     "f.createdTime, " +
                     "DATE '1970-01-01' + (((f.sysTime / 64) + 1735689600) / 86400) AS SYS_TIMESTAMP, " +
                     "DATE '1970-01-01' + (((f.dlTime / 64) + 1735689600) / 86400) AS DL_TIMESTAMP " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE f.createdTime > :lastCreatedTime " +
                     "ORDER BY f.createdTime DESC FETCH FIRST 100 ROWS ONLY";
            
            Query query = em.createNativeQuery(sql);
            query.setParameter("lastCreatedTime", lastCreatedTime);
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            return result;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findByDlTime(long id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            
            String sql = "SELECT " +
                     "r.dlStation, " +
                     "r.dlName, " +
                     "f.fltMsg, " +
                     "f.fltInfo, " +
                     "f.sysTime, " +
                     "f.sysYear, " +
                     "f.dlTime, " +
                     "f.faultLevel, " +
                     "f.faultType, " +
                     "f.createdTime, " +
                     "DATE '1970-01-01' + (((f.sysTime / 64) + 1735689600) / 86400) AS SYS_TIMESTAMP, " +
                     "DATE '1970-01-01' + (((f.dlTime / 64) + 1735689600) / 86400) AS DL_TIMESTAMP " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE f.dlTime = :id";
            
            Query query = em.createNativeQuery(sql);
            query.setParameter("id", id);
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            return result;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findRelayRoomDoorEvents() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            
            String sql = "SELECT " +
                     "r.dlStation, " +
                     "r.dlName, " +
                     "f.fltMsg, " +
                     "f.fltInfo, " +
                     "f.sysTime, " +
                     "f.sysYear, " +
                     "f.dlTime, " +
                     "f.faultLevel, " +
                     "f.faultType, " +
                     "f.createdTime, " +
                     "DATE '1970-01-01' + (((f.sysTime / 64) + 1735689600) / 86400) AS SYS_TIMESTAMP, " +
                     "DATE '1970-01-01' + (((f.dlTime / 64) + 1735689600) / 86400) AS DL_TIMESTAMP " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE TRIM(f.faultType) IN ('Relay Room Door Opened', 'Relay Room Door Closed') " +
                     "AND f.createdTime >= TRUNC(CAST(SYSTIMESTAMP AT TIME ZONE 'Asia/Kolkata' AS DATE)) AND f.createdTime < TRUNC(CAST(SYSTIMESTAMP AT TIME ZONE 'Asia/Kolkata' AS DATE)) + INTERVAL '1' DAY " +
                     "ORDER BY f.createdTime DESC";
            
            Query query = em.createNativeQuery(sql);
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            return result;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findYesterdayRelayRoomDoorEvents() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            
            String sql = "SELECT " +
                     "r.dlStation, " +
                     "r.dlName, " +
                     "f.fltMsg, " +
                     "f.fltInfo, " +
                     "f.sysTime, " +
                     "f.sysYear, " +
                     "f.dlTime, " +
                     "f.faultLevel, " +
                     "f.faultType, " +
                     "f.createdTime, " +
                     "DATE '1970-01-01' + (((f.sysTime / 64) + 1735689600) / 86400) AS SYS_TIMESTAMP, " +
                     "DATE '1970-01-01' + (((f.dlTime / 64) + 1735689600) / 86400) AS DL_TIMESTAMP " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE TRIM(f.faultType) IN ('Relay Room Door Opened', 'Relay Room Door Closed') " +
                     "AND f.createdTime >= TRUNC(CAST(SYSTIMESTAMP AT TIME ZONE 'Asia/Kolkata' AS DATE)) - INTERVAL '1' DAY " +
                     "AND f.createdTime < TRUNC(CAST(SYSTIMESTAMP AT TIME ZONE 'Asia/Kolkata' AS DATE)) " +
                     "ORDER BY f.createdTime DESC";
            
            Query query = em.createNativeQuery(sql);
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            return result;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findAllRelayRoomDoorEvents() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            
            String sql = "SELECT " +
                     "r.dlStation, " +
                     "r.dlName, " +
                     "f.fltMsg, " +
                     "f.fltInfo, " +
                     "f.sysTime, " +
                     "f.sysYear, " +
                     "f.dlTime, " +
                     "f.faultLevel, " +
                     "f.faultType, " +
                     "f.createdTime, " +
                     "DATE '1970-01-01' + (((f.sysTime / 64) + 1735689600) / 86400) AS SYS_TIMESTAMP, " +
                     "DATE '1970-01-01' + (((f.dlTime / 64) + 1735689600) / 86400) AS DL_TIMESTAMP " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE TRIM(f.faultType) IN ('Relay Room Door Opened', 'Relay Room Door Closed') " +
                     "ORDER BY f.createdTime DESC";
            
            Query query = em.createNativeQuery(sql);
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            return result;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findTodaysFaults() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            
            String sql = "SELECT " +
                     "r.dlStation, " +
                     "r.dlName, " +
                     "f.fltMsg, " +
                     "f.fltInfo, " +
                     "f.sysTime, " +
                     "f.sysYear, " +
                     "f.dlTime, " +
                     "f.faultLevel, " +
                     "f.faultType, " +
                     "f.createdTime, " +
                     "DATE '1970-01-01' + (((f.sysTime / 64) + 1735689600) / 86400) AS SYS_TIMESTAMP, " +
                     "DATE '1970-01-01' + (((f.dlTime / 64) + 1735689600) / 86400) AS DL_TIMESTAMP " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE f.faultPseudoType NOT IN ('VOLTAGE LOW', 'VOLTAGE HIGH','TEMPERATURE HIGH','TEMPERATURE LOW') " +
                     "AND f.createdTime >= TRUNC(CAST(SYSTIMESTAMP AT TIME ZONE 'Asia/Kolkata' AS DATE)) AND f.createdTime < TRUNC(CAST(SYSTIMESTAMP AT TIME ZONE 'Asia/Kolkata' AS DATE)) + INTERVAL '1' DAY " +
                     "ORDER BY f.createdTime DESC";
            
            Query query = em.createNativeQuery(sql);
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            return result;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findYesterdaysFaults() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            
            String sql = "SELECT " +
                     "r.dlStation, " +
                     "r.dlName, " +
                     "f.fltMsg, " +
                     "f.fltInfo, " +
                     "f.sysTime, " +
                     "f.sysYear, " +
                     "f.dlTime, " +
                     "f.faultLevel, " +
                     "f.faultType, " +
                     "f.createdTime, " +
                     "DATE '1970-01-01' + (((f.sysTime / 64) + 1735689600) / 86400) AS SYS_TIMESTAMP, " +
                     "DATE '1970-01-01' + (((f.dlTime / 64) + 1735689600) / 86400) AS DL_TIMESTAMP " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE f.faultPseudoType NOT IN ('VOLTAGE LOW', 'VOLTAGE HIGH','TEMPERATURE HIGH','TEMPERATURE LOW') " +
                     "AND f.createdTime >= TRUNC(CAST(SYSTIMESTAMP AT TIME ZONE 'Asia/Kolkata' AS DATE)) - INTERVAL '1' DAY " +
                     "AND f.createdTime < TRUNC(CAST(SYSTIMESTAMP AT TIME ZONE 'Asia/Kolkata' AS DATE)) " +
                     "ORDER BY f.createdTime DESC";
            
            Query query = em.createNativeQuery(sql);
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            return result;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findLast15MinutesFaults() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            
            String sql = "SELECT " +
                     "r.dlStation, " +
                     "r.dlName, " +
                     "f.fltMsg, " +
                     "f.fltInfo, " +
                     "f.sysTime, " +
                     "f.sysYear, " +
                     "f.dlTime, " +
                     "f.faultLevel, " +
                     "f.faultType, " +
                     "f.createdTime, " +
                     "DATE '1970-01-01' + (((f.sysTime / 64) + 1735689600) / 86400) AS SYS_TIMESTAMP, " +
                     "DATE '1970-01-01' + (((f.dlTime / 64) + 1735689600) / 86400) AS DL_TIMESTAMP " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE f.faultPseudoType NOT IN ('VOLTAGE LOW', 'VOLTAGE HIGH','TEMPERATURE HIGH','TEMPERATURE LOW') " +
                     "AND f.createdTime >= CAST(SYSTIMESTAMP AT TIME ZONE 'Asia/Kolkata' AS DATE) - INTERVAL '15' MINUTE " +
                     "ORDER BY f.createdTime DESC";
            
            Query query = em.createNativeQuery(sql);
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            return result;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findLast15MinutesFaultsByDlTime() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            
            String sql = "SELECT " +
                     "r.dlStation, " +
                     "r.dlName, " +
                     "f.fltMsg, " +
                     "f.fltInfo, " +
                     "f.sysTime, " +
                     "f.sysYear, " +
                     "f.dlTime, " +
                     "f.faultLevel, " +
                     "f.faultType, " +
                     "f.createdTime, " +
                     "DATE '1970-01-01' + (((f.sysTime / 64) + 1735689600) / 86400) AS SYS_TIMESTAMP, " +
                     "DATE '1970-01-01' + (((f.dlTime / 64) + 1735689600) / 86400) AS DL_TIMESTAMP " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE f.faultPseudoType NOT IN ('VOLTAGE LOW', 'VOLTAGE HIGH','TEMPERATURE HIGH','TEMPERATURE LOW') " +
                     "AND f.dlTime >= ((EXTRACT(EPOCH FROM (CAST(SYSTIMESTAMP AT TIME ZONE 'Asia/Kolkata' AS DATE) - INTERVAL '15' MINUTE)) - 1735689600) * 64) " +
                     "ORDER BY f.dlTime DESC";
            
            Query query = em.createNativeQuery(sql);
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            return result;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findFaultsByDateRange(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            
            String sql = "SELECT " +
                    "r.dlStation, " +
                    "r.dlName, " +
                    "f.fltMsg, " +
                    "f.fltInfo, " +
                    "f.sysTime, " +
                    "f.sysYear, " +
                    "f.dlTime, " +
                    "f.faultLevel, " +
                    "f.faultType, " +
                    "f.createdTime, " +
                    "DATE '1970-01-01' + (((f.sysTime / 64) + 1735689600) / 86400) AS SYS_TIMESTAMP, " +
                    "DATE '1970-01-01' + (((f.dlTime / 64) + 1735689600) / 86400) AS DL_TIMESTAMP " +
                    "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                    "WHERE f.dlTime BETWEEN " +
                    "(((CAST(:startTime AS DATE) - DATE '1970-01-01') * 86400 * 1000 - 1735689600000) / 1000) * 64 " +
                    "AND " +
                    "(((CAST(:endTime AS DATE) - DATE '1970-01-01') * 86400 * 1000 - 1735689600000) / 1000) * 64 " +
                    "AND f.faultPseudoType NOT IN ('VOLTAGE LOW', 'VOLTAGE HIGH','TEMPERATURE HIGH','TEMPERATURE LOW') " +
                    "ORDER BY f.dlTime DESC";
            
            Query query = em.createNativeQuery(sql);
            query.setParameter("startTime", fromDateTime);
            query.setParameter("endTime", toDateTime);
            @SuppressWarnings("unchecked")
            List<Object[]> result = query.getResultList();
            return result;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}