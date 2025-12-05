package se.yrgo.teachers;

/**
 * A record to store the questions that are read from the files
 * dev_tools_questions.txt and oop_questions.txt.
 */
public record Question(
                String question,
                String answer,
                Subject category,
                int points,
                String flavorText) {

        /**
         * A method that displays all newline sequences as actual line breaks in
         * question.
         * 
         * @return the text in String question with actual line breaks.
         */
        public String displayQuestion() {
                return question;
        }

        /**
         * A method that displays all newline sequences as actual line breaks in
         * flavorText.
         * 
         * @return the text in String flavorText with actual line breaks.
         */
        public String displayFlavorText() {
                return flavorText;
        }
}
