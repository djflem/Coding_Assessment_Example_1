-- The EXTRACT function in SQL is used to retrieve specific parts of a date or time, such as year, month, day, hour, minute, second, etc. When used with EPOCH, it specifically extracts the total number of seconds from a time interval.
--
-- Here’s a detailed breakdown of how EXTRACT(EPOCH FROM (p.end_dt::timestamp - p.start_dt::timestamp)) works:
--
--     p.end_dt::timestamp and p.start_dt::timestamp:
--         The ::timestamp syntax casts the start_dt and end_dt values, which are strings in VARCHAR format, into proper timestamp data types so they can be used in date arithmetic.
--
--     (p.end_dt::timestamp - p.start_dt::timestamp):
--         This part calculates the time difference between the end_dt and start_dt, resulting in an interval. An interval represents the duration between two timestamps (like "2 hours, 15 minutes").
--
--     EXTRACT(EPOCH FROM ...):
--         EXTRACT is used to pull specific components out of a date, time, or interval. When used with EPOCH, it retrieves the total time in seconds from the interval.
--         For example, if the interval represents "2 hours, 15 minutes," EXTRACT(EPOCH FROM ...) will return 8100 seconds (2 hours * 3600 seconds/hour + 15 minutes * 60 seconds/minute).
--
--     SUM(EXTRACT(EPOCH FROM ...)):
--         This SUM function aggregates the extracted seconds, giving the total execution time for all processes associated with each task.
--
-- Example:
--
-- Suppose start_dt = '2023-04-09 15:11:10' and end_dt = '2023-04-09 15:26:43'.
--
--     The interval is 15 minutes and 33 seconds.
--     EXTRACT(EPOCH FROM interval) converts this to 933 seconds.
--     If multiple intervals are summed, you get the total execution time in seconds.

SELECT
    t.hash,
    SUM(EXTRACT(EPOCH FROM (p.end_dt::timestamp - p.start_dt::timestamp))) AS usage_time
FROM
    tasks t
        JOIN
    processes p ON t.id = p.task_id
GROUP BY
    t.hash
ORDER BY
    usage_time DESC;