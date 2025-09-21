package com.datalogger.faults.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import java.sql.Timestamp;
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
        String sql = "SELECT r.dlStation, r.dlName, f.fltMsg, f.fltInfo, f.sysTime, f.sysYear, f.dlTime, f.faultLevel, f.faultType, f.createdTime " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE f.faultPseudoType NOT IN ('VOLTAGE LOW', 'VOLTAGE HIGH','TEMPERATURE HIGH') " +
                     "ORDER BY f.sysTime DESC";
        
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery(sql);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findNewFaultsSince(Timestamp lastCreatedTime) {
        String sql = "SELECT r.dlStation, r.dlName, f.fltMsg, f.fltInfo, f.sysTime, f.sysYear, f.dlTime, f.faultLevel, f.faultType, f.createdTime " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE f.createdTime > :lastCreatedTime " +
                     "ORDER BY f.createdTime DESC FETCH FIRST 100 ROWS ONLY";
        
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("lastCreatedTime", lastCreatedTime);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findByDlTime(long id) {
        String sql = "SELECT r.dlStation, r.dlName, f.fltMsg, f.fltInfo, f.sysTime, f.sysYear, f.dlTime, f.faultLevel, f.faultType, f.createdTime " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE f.dlTime = :id";
        
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery(sql);
            query.setParameter("id", id);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findRelayRoomDoorEvents() {
        String sql = "SELECT r.dlStation, r.dlName, f.fltMsg, f.fltInfo, f.sysTime, f.sysYear, f.dlTime, f.faultLevel, f.faultType, f.createdTime " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE TRIM(f.faultType) IN ('Relay Room Door Opened', 'Relay Room Door Closed') " +
                     "AND f.createdTime >= TRUNC(SYSDATE) AND f.createdTime < TRUNC(SYSDATE) + INTERVAL '1' DAY " +
                     "ORDER BY f.createdTime DESC";
        
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery(sql);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findYesterdayRelayRoomDoorEvents() {
        String sql = "SELECT r.dlStation, r.dlName, f.fltMsg, f.fltInfo, f.sysTime, f.sysYear, f.dlTime, f.faultLevel, f.faultType, f.createdTime " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE TRIM(f.faultType) IN ('Relay Room Door Opened', 'Relay Room Door Closed') " +
                     "AND f.createdTime >= TRUNC(SYSDATE - INTERVAL '1' DAY) AND f.createdTime < TRUNC(SYSDATE) " +
                     "ORDER BY f.createdTime DESC";
        
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery(sql);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findAllRelayRoomDoorEvents() {
        String sql = "SELECT r.dlStation, r.dlName, f.fltMsg, f.fltInfo, f.sysTime, f.sysYear, f.dlTime, f.faultLevel, f.faultType, f.createdTime " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE TRIM(f.faultType) IN ('Relay Room Door Opened', 'Relay Room Door Closed') " +
                     "ORDER BY f.createdTime DESC";
        
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery(sql);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    @Override
    public List<Object[]> findTodaysFaults() {
        String sql = "SELECT r.dlStation, r.dlName, f.fltMsg, f.fltInfo, f.sysTime, f.sysYear, f.dlTime, f.faultLevel, f.faultType, f.createdTime " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE f.createdTime >= TRUNC(SYSDATE) AND f.createdTime < TRUNC(SYSDATE) + INTERVAL '1' DAY " +
                     "ORDER BY f.createdTime DESC";
        
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery(sql);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    @Override
    public List<Object[]> findYesterdaysFaults() {
        String sql = "SELECT r.dlStation, r.dlName, f.fltMsg, f.fltInfo, f.sysTime, f.sysYear, f.dlTime, f.faultLevel, f.faultType, f.createdTime " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE f.createdTime >= TRUNC(SYSDATE - INTERVAL '1' DAY) AND f.createdTime < TRUNC(SYSDATE) " +
                     "ORDER BY f.createdTime DESC";
        
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery(sql);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    @Override
    public List<Object[]> findLast15MinutesFaults() {
        String sql = "SELECT r.dlStation, r.dlName, f.fltMsg, f.fltInfo, f.sysTime, f.sysYear, f.dlTime, f.faultLevel, f.faultType, f.createdTime " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE f.createdTime >= SYSDATE - INTERVAL '15' MINUTE " +
                     "ORDER BY f.createdTime DESC";
        
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery(sql);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Object[]> findLast15MinutesFaultsByDlTime() {
        // Frontend conversion logic:
        // epoch = new Date('2025-01-01T00:00:00Z').getTime() = 1735689600000 ms
        // dlTime to date: totalMilliseconds = (dlTime / 64) * 1000, actualDate = new Date(epoch + totalMilliseconds)
        // Date to dlTime: dlTime = ((currentTimeMs - epoch) / 1000) * 64
        
        // To get last 15 minutes data:
        // 1. Convert current time to dlTime format
        // 2. Convert (current time - 15 minutes) to dlTime format  
        // 3. Filter data between these two dlTime values
        
        // Oracle SQL calculation:
        // Current dlTime = ((SYSDATE - DATE '1970-01-01') * 86400 * 1000 - 1735689600000) / 1000 * 64
        // 15 minutes ago dlTime = ((SYSDATE - INTERVAL '15' MINUTE - DATE '1970-01-01') * 86400 * 1000 - 1735689600000) / 1000 * 64
        
        String sql = "SELECT r.dlStation, r.dlName, f.fltMsg, f.fltInfo, f.sysTime, f.sysYear, f.dlTime, f.faultLevel, f.faultType, f.createdTime " +
                     "FROM Fault f JOIN RhSetup r ON f.dlNo = r.dlNo " +
                     "WHERE f.dlTime >= (((SYSDATE - INTERVAL '15' MINUTE - DATE '1970-01-01') * 86400 * 1000 - 1735689600000) / 1000) * 64 " +
                     "AND f.dlTime <= (((SYSDATE - DATE '1970-01-01') * 86400 * 1000 - 1735689600000) / 1000) * 64 " +
                     "ORDER BY f.dlTime DESC";
        
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery(sql);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}