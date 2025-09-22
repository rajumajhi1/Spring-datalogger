package com.datalogger.faults.repo;

import com.datalogger.faults.model.Fault;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface FaultRepository {
    List<Object[]> allFaults();
    List<Object[]> findNewFaultsSince(Timestamp lastCreatedTime);
    List<Object[]> findByDlTime(long id);
    List<Object[]> findRelayRoomDoorEvents();
    List<Object[]> findYesterdayRelayRoomDoorEvents();
    List<Object[]> findAllRelayRoomDoorEvents();
    List<Object[]> findTodaysFaults();
    List<Object[]> findYesterdaysFaults();
    List<Object[]> findLast15MinutesFaults();
    List<Object[]> findLast15MinutesFaultsByDlTime();
    List<Object[]> findFaultsByDateRange(LocalDateTime fromDateTime, LocalDateTime toDateTime);
}