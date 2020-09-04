Dim path
Dim file
path = Pathfinder.locate(".") 
file = path&"\data.xlsx"
'import data from excel into datasheet
importToDatatable file, "data", "RunTest"
