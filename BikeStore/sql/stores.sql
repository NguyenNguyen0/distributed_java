USE [BikeStores]
SET IDENTITY_INSERT [dbo].[stores] ON
INSERT [dbo].[stores] ([store_id], [store_name], [phone], [email], [street], [city], [state], [zip_code]) VALUES (1, N'Santa Cruz Bikes', N'(831) 476-4321', N'santacruz@bikes.shop', N'3700 Portola Drive', N'Santa Cruz', N'CA', N'95060')
INSERT [dbo].[stores] ([store_id], [store_name], [phone], [email], [street], [city], [state], [zip_code]) VALUES (2, N'Baldwin Bikes', N'(516) 379-8888', N'baldwin@bikes.shop', N'4200 Chestnut Lane', N'Baldwin', N'NY', N'11432')
INSERT [dbo].[stores] ([store_id], [store_name], [phone], [email], [street], [city], [state], [zip_code]) VALUES (3, N'Rowlett Bikes', N'(972) 530-5555', N'rowlett@bikes.shop', N'8000 Fairway Avenue', N'Rowlett', N'TX', N'75088')
SET IDENTITY_INSERT [dbo].[stores] OFF

