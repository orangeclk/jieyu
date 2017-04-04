if Meteor.isServer
  Meteor.methods
    authorsAnalyze: (authorNames) ->
      HTTP.post 'http://localhost:8080/authors-analyze', {data: authorNames}
    addNewAuthor: (newAuthor) ->
      HTTP.post 'http://localhost:8080/add-new-author', {data: newAuthor}
    locationsAnalyze: (locationNames) ->
      HTTP.post 'http://localhost:8080/locations-analyze', {data: locationNames}
    addNewLocation: (name, parentName) ->
      HTTP.post 'http://localhost:8080/add-new-location', {data: {name: name, parentName: parentName}}
    peoplesAnalyze: (peopleNames) ->
      HTTP.post 'http://localhost:8080/peoples-analyze', {data: peopleNames}
    addNewPeople: (newPeople) ->
      HTTP.post 'http://localhost:8080/add-new-people', {data: newPeople}
    topicAnalyze: (authorIds)->
      HTTP.post 'http://localhost:8080/topic-analyze', {data:authorIds}
    topicFind: (topicQuery) ->
      HTTP.post 'http://localhost:8080/topic-find', {data: topicQuery}
    submitArticle: (para) ->
      HTTP.post 'http://localhost:8080/submit-article', {data: para}

    addNewTopic: (topicName) ->
      HTTP.post 'http://localhost:8080/add-new-topic', {data:topicName}
    peopleSubmit: (peopleIds) ->
      HTTP.post 'http://localhost:8080/people-submit', {data: peopleIds}
    getTopicNameById: (topicId) ->
      HTTP.post 'http://localhost:8080/get-topic-name-by-id', {data: topicId}