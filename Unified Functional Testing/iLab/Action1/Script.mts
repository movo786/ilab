'generic keyword driven test that can be driven from alm
'test data can be passed in from a datasheet,db or excel file
openBrowser Parameter("browser")
navigateToPage "https://www.ilabquality.com/"
clickCareers()
selectCountry Parameter("country") @@ hightlight id_;_Browser("Home Page | iLAB").Page("CAREERS | iLAB").Link("South Africa")_;_script infofile_;_ZIP::ssf2.xml_;_
selectJob()
clickApply()
