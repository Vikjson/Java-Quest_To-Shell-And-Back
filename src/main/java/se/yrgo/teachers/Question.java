package se.yrgo.teachers;

public record Question(
                String question,
                String answer,
                Subject category,
                int points,
                String flavorText) {
 
        /**
         * A method that displays all newline sequences as actual line breaks in
         * String question.
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

