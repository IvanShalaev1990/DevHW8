WITH project_durations AS (
    SELECT 
    ID,
    EXTRACT( MONTH FROM AGE(FINISH_DATE, START_DATE)) AS MONTH_COUNT
    FROM project
)
 SELECT
    ID as name,
    MONTH_COUNT
 FROM
    project_durations
 WHERE
    MONTH_COUNT = ( SELECT
            MAX( MONTH_COUNT )
            FROM project_durations );