if Meteor.isClient

  require './summernote-zh-CN.min.js'

  Session.setDefault 'isOldTopic', true
  Session.setDefault 'parentTopicId', -1
  Session.setDefault 'relatedTopicIds', []

  checkOldTopic = ->
    Session.set 'isOldTopic', true
    Meteor.call 'topicAnalyze', (Session.get 'authorIds'), (err, res) ->
      if err
        console.log err
      if res.data.length > 0
        Session.set 'topics', res.data
        #$('#js-relatedTopics').tagsinput 'refresh'
        Session.set 'mainTopicId', res.data[0].id

  Template.body.helpers
    isOldTopic: -> Session.get 'isOldTopic'
    selectedMainTopic: -> Session.get 'selectedMainTopic'
    relatedTopics: -> Session.get 'relatedTopics'
    test: -> Session.get 'test'
    authorPicks: -> Session.get 'authorPicks'
    locationCandidates: -> Session.get 'locationCandidates'
    peoplePicks: -> Session.get 'peoplePicks'
    topics: -> Session.get 'topics'
    mainTopicName: -> Session.get 'mainTopicName'

  Template.editor.rendered = ->
    $('.summernote').summernote
      lang: 'zh-CN'
      height: 800
      callbacks:
        onBlur: () ->
          content = $('.summernote').summernote 'code'
          Session.set 'content', content

  Template.body.events

    'change #js-title': (event) ->
      Session.set 'title', event.target.value

    'change #js-authors': (event) ->
      authorsName = (event.target.value).split ' '
      Meteor.call 'authorsAnalyze', authorsName, (err, res) ->
        if err
          console.log err
        Session.set 'authorPicks', res.data
        Session.set 'authorIds', (-1 for [1..res.data.length])

    'change input[id^=js-authorRadios]': (event) ->
      candidateIndex = parseInt(event.target.value)
      authorIndex = parseInt (((event.target.id).split '-')[2])
      pick = (Session.get 'authorPicks')[authorIndex]
      authorIds = Session.get 'authorIds'
      authorIds[authorIndex] = pick.candidates[candidateIndex].id
      Session.set 'authorIds', authorIds
      checkOldTopic()

    'click button[id^=js-addNewAuthor]': (event) ->
      event.preventDefault()
      authorIndex = parseInt((event.target.id.split '-')[2])
      authorPicks = Session.get 'authorPicks'
      newAuthor =
        name: authorPicks[authorIndex].name
        description: $('#js-newAuthorDesc-' + authorIndex).val()
      Meteor.call 'addNewAuthor', newAuthor, (err, res) ->
        if err
          console.log err
        authorPicks[authorIndex].candidates.push res.data
        authorPicks[authorIndex].pick = authorPicks[authorIndex].candidates.length - 1 #TODO set the radio view to the pick index
        Session.set 'authorPicks', authorPicks

    'change #js-locations': (event) ->
      locationNames = event.target.value.split ' '
      Meteor.call 'locationsAnalyze', locationNames, (err, res) ->
        if err
          console.log err
        locations = res.data
        Session.set 'locationIds', locations.map (location) ->
          if location?
            location.id
          else
            -1
        locationCandidates = []
        for locationName, i in locationNames
          locationCandidates[i] =
            name: locationName
            location: locations[i]
        Session.set 'locationCandidates', locationCandidates

    'click button[id^=js-addNewLocation]': (event) ->
      event.preventDefault()
      locationIndex = parseInt((event.target.id.split '-')[2])
      candidates = Session.get 'locationCandidates'
      name = candidates[locationIndex].name
      parentName = $('#js-newLocationDesc-' + locationIndex).val()
      Meteor.call 'addNewLocation', name, parentName, (err, res) ->
        if err
          console.log err
        console.log res.data
        candidates[locationIndex].location = res.data
        Session.set 'locationCandidates', candidates
        locationIds = Session.get 'locationIds'
        locationIds[locationIndex] = res.data.id
        Session.set 'locationIds', locationIds

    'change #js-peoples': (event) ->
      peopleNames = event.target.value.split ' '
      Meteor.call 'peoplesAnalyze', peopleNames, (err, res) ->
        if err
          console.log err
        peoplePicks = res.data
        Session.set 'peoplePicks', peoplePicks
        Session.set 'peopleIds', (-1 for [1..peoplePicks.length])

    'change input[id^=js-peopleRadios]': (event) ->
      candidateIndex = parseInt event.target.value
      peopleIndex = parseInt(((event.target.id).split '-')[2])
      pick = (Session.get 'peoplePicks')[peopleIndex]
      peopleIds = Session.get 'peopleIds'
      peopleIds[peopleIndex] = pick.candidates[candidateIndex].id
      Session.set 'peopleIds', peopleIds

    'click button[id^=js-addNewPeople]': (event) ->
      event.preventDefault()
      peopleIndex = parseInt ((event.target.id.split '-')[2])
      peoplePicks = Session.get 'peoplePicks'
      newPeople =
        name: peoplePicks[peopleIndex].name
        description: $('#js-newPeopleDesc-' + peopleIndex).val()
      Meteor.call 'addNewPeople', newPeople, (err, res) ->
        if err
          console.log err
        peoplePicks[peopleIndex].candidates.push res.data
        peoplePicks[peopleIndex].pick = peoplePicks[peopleIndex].candidates.length - 1 #TODO set the radio view
        Session.set 'peoplePicks', peoplePicks

    'change #js-oldTopic': ->
      checkOldTopic()

    'change #js-newTopic': ->
      Session.set 'isOldTopic', false
      #$('#js-relatedTopicsNewTopic').tagsinput({itemValue: 'id', itemText: 'name'})

    'change #js-mainTopicSelect': (event) ->
      Session.set 'selectedMainTopic', event.target.value
      topics = Session.get 'topics'
      Session.set 'mainTopicId', topics[event.target.selectedIndex].id
      Session.set 'mainTopicName', topics[event.target.selectedIndex].name
      $('#js-mainTopicName').show()

    'submit #js-oldMainTopic': (event) ->
      event.preventDefault()
      Meteor.call 'topicFind', event.target.text.value, (err, res) ->
        Session.set 'mainTopicId', res.data.id
        Session.set 'mainTopicName', res.data.name
        $('#js-mainTopicName').show()

    'submit #js-relatedTopicsForm': (event) ->
      event.preventDefault()
      Meteor.call 'topicFind', event.target.text.value, (err, res) ->
        relatedTopics = $('js-relatedTopics')
        relatedTopics.tagsinput
          itemValue: 'id'
          itemText: 'name'
        relatedTopics.tagsinput 'add', res.data


    'change #js-relatedTopics': (event) ->
      Session.set 'relatedTopics', $('#js-relatedTopics').val()

    'change #js-relatedTopicSelect': (event) ->
      topics = Session.get 'topics'
      $('#js-relatedTopicsNewTag').tagsinput
        itemValue: 'id'
        itemText: 'name'
      $('#js-relatedTopicsNewTag').tagsinput 'add', topics[event.target.selectedIndex]

    'submit #js-newTopic': (event) ->
      event.preventDefault()
      Meteor.call 'addNewTopic', event.target.text.value, (err, res) ->
        if err
          console.log err
        Session.set 'mainTopicId', res.data
        $('#newTopic').show()

    'submit #js-newParentTopic': (event) ->
      event.preventDefault()
      Meteor.call 'topicFind', event.target.text.value, (err, res) ->
        if err
          console.log err
        if res.data?
          Session.set 'parentTopicId', res.data.id
          console.log res.data.id
          $('#parentTopic').show()
        else
          $('#parentTopic-no-exist').show()

    'submit #js-relatedTopicsNewForm': (event) ->
      event.preventDefault()
      relatedStr = event.target.text.value
      Meteor.call 'topicFind', relatedStr, (err, res) ->
        if err
          console.log err
        console.log res.data.id
        topics = $('#js-relatedTopicsNewTag')
        topics.tagsinput
          itemValue: 'id'
          itemText: 'name'
        topics.tagsinput 'add', res.data

    'submit #js-submitArticle': (event) ->
      event.preventDefault()
      relatedTopics = []
      if Session.get 'isOldTopic'
        relatedTopics = $('#js-relatedTopics').tagsinput 'item'
      else
        relatedTopics = $('#js-relatedTopicsNewTag').tagsinput 'item'
      if not relatedTopics?
        relatedTopics = []
      console.log relatedTopics
      relatedTopicIds = relatedTopics.map (topic) ->
        topic.id
      Session.set 'relatedTopicIds', relatedTopicIds

      para =
        title: Session.get 'title'
        authorIds: Session.get 'authorIds'
        locationIds: Session.get 'locationIds'
        content: Session.get 'content'
        topicId: Session.get 'mainTopicId'
        parentTopicId: Session.get 'parentTopicId'
        relatedTopicIds: Session.get 'relatedTopicIds'
        peopleIds: Session.get 'peopleIds'
      Meteor.call 'submitArticle', para, (err, res) ->
        if err
          console.log para
          console.log err

#TODO people auto-detect