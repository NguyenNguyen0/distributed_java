USE [BikeStores]

SET IDENTITY_INSERT [dbo].[brands] ON 

INSERT [dbo].[brands] ([brand_id], [brand_name]) VALUES (1, N'Electra')
INSERT [dbo].[brands] ([brand_id], [brand_name]) VALUES (2, N'Haro')
INSERT [dbo].[brands] ([brand_id], [brand_name]) VALUES (3, N'Heller')
INSERT [dbo].[brands] ([brand_id], [brand_name]) VALUES (4, N'Pure Cycles')
INSERT [dbo].[brands] ([brand_id], [brand_name]) VALUES (5, N'Ritchey')
INSERT [dbo].[brands] ([brand_id], [brand_name]) VALUES (6, N'Strider')
INSERT [dbo].[brands] ([brand_id], [brand_name]) VALUES (7, N'Sun Bicycles')
INSERT [dbo].[brands] ([brand_id], [brand_name]) VALUES (8, N'Surly')
INSERT [dbo].[brands] ([brand_id], [brand_name]) VALUES (9, N'Trek')
SET IDENTITY_INSERT [dbo].[brands] OFF
SET IDENTITY_INSERT [dbo].[categories] ON 

INSERT [dbo].[categories] ([category_id], [category_name]) VALUES (1, N'Children Bicycles')
INSERT [dbo].[categories] ([category_id], [category_name]) VALUES (2, N'Comfort Bicycles')
INSERT [dbo].[categories] ([category_id], [category_name]) VALUES (3, N'Cruisers Bicycles')
INSERT [dbo].[categories] ([category_id], [category_name]) VALUES (4, N'Cyclocross Bicycles')
INSERT [dbo].[categories] ([category_id], [category_name]) VALUES (5, N'Electric Bikes')
INSERT [dbo].[categories] ([category_id], [category_name]) VALUES (6, N'Mountain Bikes')
INSERT [dbo].[categories] ([category_id], [category_name]) VALUES (7, N'Road Bikes')
SET IDENTITY_INSERT [dbo].[categories] OFF