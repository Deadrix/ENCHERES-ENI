IF EXISTS
    (
        SELECT
            *
        FROM
            sys.objects
        WHERE
            object_id = OBJECT_ID(N'[dbo].[RETRAITS]')
        AND type IN (
                        N'U'
                    )
    )
    DROP TABLE [dbo].[RETRAITS]
GO
IF EXISTS
    (
        SELECT
            *
        FROM
            sys.objects
        WHERE
            object_id = OBJECT_ID(N'[dbo].[ENCHERES]')
        AND type IN (
                        N'U'
                    )
    )
    DROP TABLE [dbo].[ENCHERES]
GO
IF EXISTS
    (
        SELECT
            *
        FROM
            sys.objects
        WHERE
            object_id = OBJECT_ID(N'[dbo].[ARTICLES_VENDUS]')
        AND type IN (
                        N'U'
                    )
    )
    DROP TABLE [dbo].[ARTICLES_VENDUS]
GO
IF EXISTS
    (
        SELECT
            *
        FROM
            sys.objects
        WHERE
            object_id = OBJECT_ID(N'[dbo].[CATEGORIES]')
        AND type IN (
                        N'U'
                    )
    )
    DROP TABLE [dbo].[CATEGORIES]
GO
IF EXISTS
    (
        SELECT
            *
        FROM
            sys.objects
        WHERE
            object_id = OBJECT_ID(N'[dbo].[UTILISATEURS]')
        AND type IN (
                        N'U'
                    )
    )
    DROP TABLE [dbo].[UTILISATEURS]
GO
