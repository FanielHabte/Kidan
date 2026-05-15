const validationsContainer = document.querySelector(".nc-form-column-validations-container");
const addValidationButton = document.querySelector(".nc-add-column");
const form = document.querySelector(".nc-form")
let counter = 0;

function addValidationColumn() {
    counter++;
    const original = document.querySelector("#column-0");
    const newValidation = original.cloneNode(true);
    newValidation.id = `column-${counter}`
    const inputs = newValidation.querySelectorAll("input, select, textarea");
    inputs.forEach(field => {
        field.name =  field.name.replace(/\[\d+]/, `[${counter}]`);
    })

    validationsContainer.append(newValidation);
}

addValidationButton.addEventListener("click", addValidationColumn);

validationsContainer.addEventListener("click", (event) => {
    if (event.target.classList.contains("nc-remove-column")) {
        event.target.closest(".nc-form-column-validation").remove();
    }
});

validationsContainer.addEventListener("change", (event) => {
    if (event.target.id === "column-data-type") {
        const dataType = event.target.value;
        const currentRow = event.target.closest(".nc-form-column-validation");
        const formatFields = currentRow.querySelectorAll(".format-rule");

        formatFields.forEach(field => {
            field.classList.add("hidden");
            field.classList.remove("show");
            const inputs = field.querySelectorAll("input, select, textarea");
            inputs.forEach(input => input.disabled = true);
        });

        const targetClass = `.${dataType}-format`;
        const targetField = currentRow.querySelector(targetClass);
        if (targetField) {
            targetField.classList.remove("hidden");
            targetField.classList.add("show");
            const inputs = targetField.querySelectorAll("input, select, textarea");
            inputs.forEach(input => input.disabled = false);
        }
    }
});


