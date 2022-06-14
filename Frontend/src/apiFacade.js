const URL = "http://localhost:8080/devops_starter_war_exploded";

function handleHttpErrors(res) {
    if (!res.ok) {
        return Promise.reject({ status: res.status, fullError: res.json() })
    }
    return res.json();
}

function apiFacade() {
    /* Insert utility-methods from a later step (d) here (REMEMBER to uncomment in the returned object when you do)*/

    const setToken = (token) => {
        localStorage.setItem('jwtToken', token)
    }
    const getToken = () => {
        return localStorage.getItem('jwtToken')
    }
    const loggedIn = () => {
        const loggedIn = getToken() != null;
        return loggedIn;
    }
    const logout = () => {
        localStorage.removeItem("jwtToken");
    }

    const login = (user, password) => {
        const options = makeOptions("POST", true, { username: user, password: password });
        return fetch(URL + "/api/login", options)
            .then(handleHttpErrors)
            .then(res => { setToken(res.token) })
    }

    const loginData = () => {
        const options = makeOptions("GET", true); //True add's the token
        return fetch(URL + "/api/info/user", options).then(handleHttpErrors);
        //Change return fetch(URL + "api/info/user", to something like URL + "api/info/" + {userrole}?? this will change the you are logged in as (userrole) message)
    }

    const fetchData = (endpoint) => {
        const options = makeOptions("GET", true); //True add's the token
        return fetch(URL + "/api/xxx/" + endpoint, options).then(handleHttpErrors);

    }

    const postData = (endpoint, value) => {
        const options = makeOptions("POST", true, { body: value });
        return fetch(URL + "/api/xxx/" + endpoint, options)
            .then(handleHttpErrors)
            .then(res => { setToken(res.token) })
    }

    const deleteData = (endpoint) => {
        const options = makeOptions("DELETE", true);
        return fetch(URL + "/api/xxx/" + endpoint, options).then(handleHttpErrors);
    }

    const makeOptions = (method, addToken, body) => {
        var opts = {
            method: method,
            headers: {
                "Content-type": "application/json",
                'Accept': 'application/json',
            }
        }
        if (addToken && loggedIn()) {
            opts.headers["x-access-token"] = getToken();
        }
        if (body) {
            opts.body = JSON.stringify(body);
        }
        return opts;
    }
    return {
        makeOptions,
        setToken,
        getToken,
        loggedIn,
        login,
        logout,
        loginData,
        fetchData,
        postData,
        deleteData
    }
}
const facade = apiFacade();
export default facade;