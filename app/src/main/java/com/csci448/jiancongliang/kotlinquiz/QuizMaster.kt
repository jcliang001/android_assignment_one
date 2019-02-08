package com.csci448.jiancongliang.kotlinquiz


object QuizMaster {

    private val questionBank: MutableList<Question> = mutableListOf()
    // create a instance of the com.csci448.jiancongliang.kotlinquiz.Question and pass the string and boolean variable into the constructor
     var currentScore = 0
     var currentQuestionIndex = 0
    init{
        questionBank.add(Question(R.string.question1,false))
        questionBank.add(Question(R.string.question2,true))
        questionBank.add(Question(R.string.question3,false))
        questionBank.add(Question(R.string.question4,true))

    }
    fun getCurrentQuestion() = questionBank[currentQuestionIndex]

    fun moveToNextQuestion(){
        currentQuestionIndex++
        currentQuestionIndex = (currentQuestionIndex) % (questionBank.size)
    }

    fun moveToPreviousQuestion(){

        currentQuestionIndex = if(currentQuestionIndex>0) {
            currentQuestionIndex - 1
        }
        else{
            questionBank.size
        }

    }



    fun getCurrentQuestionTextId() = getCurrentQuestion().textResId

    fun getCurrentAnswer() = getCurrentQuestion().isAnswerTrue

    fun isAnswerCorrect(answer: Boolean) =
        if(answer == getCurrentAnswer()){
            currentScore++
            true
        }

        else
            false
}





