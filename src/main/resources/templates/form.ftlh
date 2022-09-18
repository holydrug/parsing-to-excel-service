<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Spring Boot Form Submission</title>
        <link href="/css/main.css" rel="stylesheet">
    </head>
    <body>
        <h1>Spring Boot Form Submission</h1>
        <#if commonStructure?? >
            Your submitted data<br>
            username: ${commonStructure.username}<br>
            password: ${commonStructure.password}<br>
            receiver: ${commonStructure.receiver}<br>
            urlOfNBA: ${commonStructure.urlOfNBA}<br>
            urlOfSIAP: ${commonStructure.urlOfSIAP}<br>
            urlOfCHALLENGER: ${commonStructure.urlOfCHALLENGER}<br>
            urlOfSPE: ${commonStructure.urlOfSPE}<br>
        <#else>
            <form action="/form" method="post">
                <div>username:</div>
                <input type="text" name="username"/>
                <br/><br/>
                <div>password:</div>
                <textarea rows="2" name="password"></textarea>
                <br/><br/>
                <div>receiver:</div>
                <input type="text" name="receiver"/>
                <br/><br/>
                <div>urlOfNBA:</div>
                <input type="text" name="urlOfNBA"/>
                <br/><br/>
                <div>urlOfSIAP:</div>
                <input type="text" name="urlOfSIAP"/>
                <br/><br/>
                <div>urlOfCHALLENGER:</div>
                <input type="text" name="urlOfCHALLENGER"/>
                <br/><br/>
                <div>urlOfSPE:</div>
                <input type="text" name="urlOfSPE"/>
                <br/><br/>

                <input type="submit" value="Submit"/>
                <input type="reset" value="Reset"/>
            </form>
        </#if>
        <script src="/js/main.js"></script>
    </body>
</html>
