IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[RETRAITS]') AND type in (N'U'))
DROP TABLE [dbo].[RETRAITS]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ENCHERES]') AND type in (N'U'))
DROP TABLE [dbo].[ENCHERES]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ARTICLES_VENDUS]') AND type in (N'U'))
DROP TABLE [dbo].[ARTICLES_VENDUS]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CATEGORIES]') AND type in (N'U'))
DROP TABLE [dbo].[CATEGORIES]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[UTILISATEURS]') AND type in (N'U'))
DROP TABLE [dbo].[UTILISATEURS]
GO
