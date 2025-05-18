USE [BikeStores]
SET IDENTITY_INSERT [dbo].[staffs] ON 

INSERT [dbo].[staffs] ([staff_id], [first_name], [last_name], [email], [phone], [active], [store_id], [manager_id]) VALUES (1, N'Fabiola', N'Jackson', N'fabiola.jackson@bikes.shop', N'(831) 555-5554', 1, 1, NULL)
INSERT [dbo].[staffs] ([staff_id], [first_name], [last_name], [email], [phone], [active], [store_id], [manager_id]) VALUES (2, N'Mireya', N'Copeland', N'mireya.copeland@bikes.shop', N'(831) 555-5555', 1, 1, 1)
INSERT [dbo].[staffs] ([staff_id], [first_name], [last_name], [email], [phone], [active], [store_id], [manager_id]) VALUES (3, N'Genna', N'Serrano', N'genna.serrano@bikes.shop', N'(831) 555-5556', 1, 1, 2)
INSERT [dbo].[staffs] ([staff_id], [first_name], [last_name], [email], [phone], [active], [store_id], [manager_id]) VALUES (4, N'Virgie', N'Wiggins', N'virgie.wiggins@bikes.shop', N'(831) 555-5557', 1, 1, 2)
INSERT [dbo].[staffs] ([staff_id], [first_name], [last_name], [email], [phone], [active], [store_id], [manager_id]) VALUES (5, N'Jannette', N'David', N'jannette.david@bikes.shop', N'(516) 379-4444', 1, 2, 1)
INSERT [dbo].[staffs] ([staff_id], [first_name], [last_name], [email], [phone], [active], [store_id], [manager_id]) VALUES (6, N'Marcelene', N'Boyer', N'marcelene.boyer@bikes.shop', N'(516) 379-4445', 1, 2, 5)
INSERT [dbo].[staffs] ([staff_id], [first_name], [last_name], [email], [phone], [active], [store_id], [manager_id]) VALUES (7, N'Venita', N'Daniel', N'venita.daniel@bikes.shop', N'(516) 379-4446', 1, 2, 5)
INSERT [dbo].[staffs] ([staff_id], [first_name], [last_name], [email], [phone], [active], [store_id], [manager_id]) VALUES (8, N'Kali', N'Vargas', N'kali.vargas@bikes.shop', N'(972) 530-5555', 1, 3, 1)
INSERT [dbo].[staffs] ([staff_id], [first_name], [last_name], [email], [phone], [active], [store_id], [manager_id]) VALUES (9, N'Layla', N'Terrell', N'layla.terrell@bikes.shop', N'(972) 530-5556', 1, 3, 7)
INSERT [dbo].[staffs] ([staff_id], [first_name], [last_name], [email], [phone], [active], [store_id], [manager_id]) VALUES (10, N'Bernardine', N'Houston', N'bernardine.houston@bikes.shop', N'(972) 530-5557', 1, 3, 7)
SET IDENTITY_INSERT [dbo].[staffs] OFF