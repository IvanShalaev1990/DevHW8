SELECT client_name, project_count
 FROM (SELECT client.ID AS client_id, client.NAME AS client_name, COUNT(project.ID) AS project_count
    FROM client
    LEFT JOIN project ON client.ID = project.CLIENT_ID
    GROUP BY client.ID, client.NAME)
 WHERE project_count IN (SELECT MAX(project_count) FROM (SELECT client.ID AS client_id, client.NAME AS client_name, COUNT(project.ID) AS project_count
    FROM client
    LEFT JOIN project ON client.ID = project.CLIENT_ID
    GROUP BY client.ID, client.NAME));