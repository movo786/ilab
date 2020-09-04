Dim path
Dim file
path = Pathfinder.locate(".") 
file = path&"\testData.mdb"
'import test data from DB into data table
importTestDataFromDb file, "RunTest"
