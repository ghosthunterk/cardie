
const MongoClient = require('mongodb').MongoClient;
const express = require('express');
const fs = require('fs');
var jsonData = require('./cardie.json');
const DATABASE_NAME = 'Cardie';
const bodyParser = require('body-parser');
var app = express();
// app.use(bodyParser.json());
// app.use(bodyParser.urlencoded({extended:true}));
// let MongoClient = mongodb.MongoClient;

// app.use(express.json);

let url = 'mongodb://localhost:27017';


function getDataFromCSV()
{
   
    // console.log(types);
    MongoClient.connect(url,{useNewUrlParser:true},function(err,client)
    {
        if (err)
            console.log('Unable to connect to the MongoDB server. Error', err);
        else
        {
            let db = client.db(DATABASE_NAME);
            var types = [];
            datas = JSON.parse(fs.readFileSync('./cardie.json'));
            for (data in datas)
            {
                let count = 0;
                var data = 
                {
                    "name":datas[data].Type,
                    "quantity":0
                }
                let check = types.findIndex(item => item.name == data.name);
                // console.log(check);
                if (check===-1)
                {
                    types.push(data);
                }
                else
                {
                    types[check].quantity +=1;
                }
                
            }

            types.forEach(function(item,index,array){
                db.collection('Set').insertOne(item,function(err, res) {
                    if (err) throw err;
                    console.log("1 set inserted in Set");
                    
                });
            });
            client.close();
        }
        
    })
}








MongoClient.connect(url, {useNewUrlParser: true}, function(err, client){
    if(err)
        console.log('Unable to connect to the MongoDB server. Error', err);
    else{
        var db = client.db(DATABASE_NAME);

        app.post('insertData/Card',function(req,res,next){
            let post_data = request.body;
            db = client.db(DATABASE_NAME);
            db.collection('card').insertOne(post_data,function(err, res) {
                if (err) throw err;
                console.log("1 card inserted in Card");
                db.close();
            });
        });

        //Get Data
        app.get('/all-set',(request,response)=>
        {
            db.collection('Set').find({}).toArray(function(err,result){
                if (err) throw err;
                console.log(result);
                response.json(result);
            })
        });

        app.get('/DayCat',(request,response)=>
         {
         	let Cat = request.query.Cat;
         	let Day = request.query.Day;
         	let db = client.db(DATABASE_NAME);
         	db.collection('eFlashcard').find({"Category":Cat,"Day":Day}).toArray(function(err,result)
         	{
         		if (err) throw err;
         		console.log(result);
         		response.send(result);

         	});
         }); 

        app.post('/saveResult',(request,response)=>
        {
            let data = request.body;
            let email = data.email;
            let test_day = data.test_day;
            let test_cat = data.test_cat;
            let test_score = data.test_score;
            let Document =
            {
                "email": email,
                "test_day":test_day,
                "test_cat":test_cat,
                "test_score":test_score 
            }
            let db = client.db(DATABASE_NAME);
            db.collection('TestDetail').find({"email":email}).toArray(function(err,result)
                {
                    if (result!=null)
                    {
                        db.collection('TestDetail').updateOne({"email":email,"test_cat":test_cat,"test_day":test_day},
                                                    {$set: {"test_score":test_score}});
                    }
                    else{ 
                        db.collection('TestDetail').insertOne(Document);
                        response.send('done');
                    }
                });
            
        });

        
    }
});

// getDataFromCSV()
app.listen(3000, ()=>{
    console.log("Listening on port 3000...");
})