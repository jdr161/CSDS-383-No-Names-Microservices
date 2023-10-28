const { default: axios } = require('axios');
const express = require('express')
const proxy = require('express-http-proxy');

const app = express()
const port = 3000;


app.use('/test', proxy('http://google.com'))
app.use('/api/view-events', proxy('/api/view-events'))
app.use('/api/create-event', proxy('http://localhost:8080/api/create-event'))
app.use('/api/view-participants', proxy('http://localhost:8080/api/view-participants'))
app.use('/api/create-participant', proxy('http://localhost:8080/api/create-participant'))
app.use('/api/register-participant', proxy('http://localhost:8080/api/register-participant'))


// // we are only sending json requests
// app.use(express.json())

// app.get('/api/view-events', (req, res) => {
//     res.send("yep")
// })

// app.post('/api/create-event', (req, res) => {})

// app.get('/api/view-participants', (req, res) => {})

// app.post('/api/create-participant', (req, res) => {})

// app.put('/api/register-participant', (req, res) => {
//     let participantId = req.query.participantId
//     let eventId = req.query.eventId
    
//     // check if either of the parameters is missing
//     let errorMessage = ""
//     if(participantId == ""){
//         errorMessage += "participantId is a required query parameter."
//     }
//     if(eventId == ""){
//         errorMessage += "eventId is a required query parameter."
//     }
//     if(errorMessage != ""){
//         return res.status(400).send(errorMessage)
//     } else {
//         // if we have both parameters
//         let apiURL = `http://localhost:8080/api/register-participant?participantId=${participantId}&eventId=${eventId}`
//         try {
//             response = axios.put(apiURL)
//             return res.send(response)
//         } catch (error) {
//             return res.send(error.data)
//         }
//     }
// })



app.listen(port, () => {
    console.log(`API Gateway listening at http://localhost:${port}`)
})