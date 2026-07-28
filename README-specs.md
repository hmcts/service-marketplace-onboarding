# Rough file to show the request bodies that we might need to allow consumers and providers to make requests

Want to be able to retrieve list of requests so we can see what we have stored
Want to be able to delete entries so we can keep our data tidy

Lets add a requestId to each payload
Thus we can get all 
And delete for an individual request


POST /api/publish — Producer submitting an API listing
{
"fullName": "string",
"organisation": "string",
"email": "string",
"jobTitle": "string",
"phone": "string (optional)",
"apiName": "string",
"repoName": "string",
"version": "string",
"domain": "Case Administration | Hearing Results | Scheduling and Listing | Reference Data | Financial",
"classification": "string",
"description": "string"
}

POST /api/requests/new-api — Consumer requesting an API that doesn't exist yet
{
"fullName": "string",
"organisation": "string",
"email": "string",
"jobTitle": "string",
"phone": "string (optional)",
"need": "string",
"domain": "Case Administration | Hearing Results | Scheduling and Listing | Reference Data | Financial | Not sure",
"urgency": "Critical | High | Medium | Low",
"existingWorkaround": "string (optional)"
}


POST /api/contact — General contact/feedback
{
"fullName": "string",
"organisation": "string",
"email": "string",
"topic": "Onboarding question | An existing API access request | Publishing or listing an API | Data governance or a data breach | Something isn't working | General feedback | Something else",
"message": "string"
}

