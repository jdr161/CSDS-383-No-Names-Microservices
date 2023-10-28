const { default: axios } = require('axios');
const express = require('express')

const app = express()
const port = 3001;


// we are only sending json requests
app.use(express.json())

app.get('/api/view-events', (req, res) => {
    // CORS policy required to have browser accept data
    res.setHeader("Access-Control-Allow-Origin", "*")
    res.setHeader('Access-Control-Allow-Methods', '*')
    res.setHeader("Access-Control-Allow-Headers", "*")

    let apiURL = `http://localhost:8080/api/view-events`
    axios.get(apiURL).then(function (response){
        res.status(response.status).send(response.data)
    }).catch(function (error){
        console.log(error)
        res.status(error.response.status).send({
            error: {
                response: {
                    data: error.response.data
                }
            }})
    })
})

app.post('/api/create-event', (req, res) => {
    // CORS policy required to have browser accept data
    res.setHeader("Access-Control-Allow-Origin", "*")
    res.setHeader('Access-Control-Allow-Methods', '*')
    res.setHeader("Access-Control-Allow-Headers", "*")

    let apiURL = `http://localhost:8080/api/create-event`

    axios.post(apiURL, req.body).then(function (response){
        res.status(response.status).send(response.data)
    }).catch(function (error){
        console.log(error)
        res.status(error.response.status).send({
            error: {
                response: {
                    data: error.response.data
                }
            }})
    })
})

app.get('/api/view-participants', (req, res) => {
    // CORS policy required to have browser accept data
    res.setHeader("Access-Control-Allow-Origin", "*")
    res.setHeader('Access-Control-Allow-Methods', '*')
    res.setHeader("Access-Control-Allow-Headers", "*")

    let apiURL = `http://localhost:8080/api/view-participants`

    axios.get(apiURL, req.body).then(function (response){
        res.status(response.status).send(response.data)
    }).catch(function (error){
        console.log(error)
        res.status(error.response.status).send({
            error: {
                response: {
                    data: error.response.data
                }
            }})
    })
})

app.post('/api/create-participant', (req, res) => {
    // CORS policy required to have browser accept data
    res.setHeader("Access-Control-Allow-Origin", "*")
    res.setHeader('Access-Control-Allow-Methods', '*')
    res.setHeader("Access-Control-Allow-Headers", "*")

    let apiURL = `http://localhost:8080/api/view-participants`

    axios.post(apiURL, req.body).then(function (response){
        res.status(response.status).send(response.data)
    }).catch(function (error){
        console.log(error)
        res.status(error.response.status).send({
            error: {
                response: {
                    data: error.response.data
                }
            }})
    })
})

app.put('/api/register-participant', (req, res) => {
    // CORS policy required to have browser accept data
    res.setHeader("Access-Control-Allow-Origin", "*")
    res.setHeader('Access-Control-Allow-Methods', '*')
    res.setHeader("Access-Control-Allow-Headers", "*")

    let participantId = req.query.participantId
    let eventId = req.query.eventId

    let apiURL = `http://localhost:8080/api/register-participant?participantId=${participantId}&eventId=${eventId}`

    axios.put(apiURL).then(function (response){
        res.status(response.status).send(response.data)
    }).catch(function (error){
        console.log(error)
        res.status(error.response.status).send({
            error: {
                response: {
                    data: error.response.data
                }
            }})
    })
})



app.listen(port, () => {
    console.log(`API Gateway listening at http://localhost:${port}`)
})