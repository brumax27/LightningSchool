# LightningSchool

## Tavis-CI
https://travis-ci.org/brumax27/LightningSchool  [![Build Status](https://travis-ci.org/brumax27/LightningSchool.svg?branch=master)](https://travis-ci.org/brumax27/LightningSchool)

## Ticket Tracker
https://www.pivotaltracker.com/n/projects/2321665

## To login 

call uri to ``POST /api/auth/login`` with body

``
    {
        "mail":""
        "password":""
    }
``

extract header response with name "Authorization" and reuse token for call an other route 

## To try an exercise is ok

call uri to ``POST /api/verify/exercise`` with 
header ``"Authorization" => "Bearer ..." `` and body
``
    {
        "npi": "1!10|&",
        "exerciceTeacherId": 5
    }
``
