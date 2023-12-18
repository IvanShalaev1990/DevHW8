SELECT
    CASE WHEN BIRTHDAY = youngest_birthday THEN 'youngest' ELSE 'oldest' END AS Type,
    NAME,
    BIRTHDAY
 FROM worker
 JOIN (
    SELECT
        MIN(BIRTHDAY) AS youngest_birthday,
        MAX(BIRTHDAY) AS oldest_birthday
    FROM worker
) ON BIRTHDAY = oldest_birthday  OR BIRTHDAY = youngest_birthday;