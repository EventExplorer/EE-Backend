SELECT DISTINCT category_id,
                artist_name,
                COUNT(votes) as count
FROM EventExplorer.polling
GROUP BY artist_name, category_id;