
fetch('http://45.89.226.2:41031/test/greeting/')
    .then(function(response) {
        if (!response.ok) {
            throw Error(response.statusText);
        }
        return response.text();
    })
    .then(
        x=> {
            var element = document.createElement('div');
            element.innerHTML=x;
            console.log(x);
            document.body.append(element)

        })
    .catch(
        x=> {
            fetch('http://45.89.226.2:41031/ufs-user-entry/api/oidc/v1/auth/RT_SUBSIDY_RECIPIENT_WEB/esia?appId=TEST')
                .then(response=> response.text())
                .then(url=>{
                    console.log(url);
                    var element = document.createElement('a');
                    element.href = url;
                    element.innerText='АВТОРИЗАЦИЯ ЕСИА'
                    document.body.append(element)
                })

        });


