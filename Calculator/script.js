
let input = document.getElementById('inputbox');
let buttons = document.querySelectorAll('button');

let string = "";
let buttonsArray = Array.from(buttons);

buttonsArray.forEach(button => {
    button.addEventListener('click', (e) => {
        let buttonText = e.target.innerHTML;

        if (buttonText === '=') {
            try {
                string = eval(string);
                input.value = string;
            } catch {
                input.value = "Error";
                string = "";
            }
        } else if (buttonText === 'AC') {
            string = '';
            input.value = string;
        } else if (buttonText === 'Back') {
            string = string.slice(0, -1);
            input.value = string;
        } else {
            string += buttonText;
            input.value = string;
        }
    });
});
