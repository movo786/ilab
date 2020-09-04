'drive reusable actions from data loaded into datasheet from excel file
RunAction "navigateToJobs [iLab]", oneIteration, DataTable("country", dtLocalSheet), DataTable("browser", dtLocalSheet)
RunAction "verifyError [iLab]", oneIteration, DataTable("name", dtLocalSheet), DataTable("email", dtLocalSheet)
