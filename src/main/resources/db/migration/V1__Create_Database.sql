-- DO $$ BEGIN
--     IF NOT EXISTS (
--         SELECT 1
--         FROM pg_database
--         WHERE datname = 'urlshortener'
--     ) THEN
--         CREATE DATABASE urlshortener;
--     END IF;
-- END $$;
