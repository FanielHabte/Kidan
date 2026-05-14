/** Dataset Page list filtering **/

// Get selector that contains options
const datasetTypeTypeSelection = document.getElementById("dataset-type-filter")
datasetTypeTypeSelection.addEventListener("change", (event) => {
    // On change event get target value and normalize it
    const selectedType = event.target.value.trim().toUpperCase();
    // Get all table rows
    const tableRows = document.querySelectorAll(".datasets-table tbody tr")
    // Logic: For each rows show if selection is All or the same as value
    // If not hide it
    tableRows.forEach(row => {
        const typePill = row.querySelector(".dataset-type-pill")
        if (typePill) {
            const rowValue = typePill.textContent.trim().toUpperCase();
            if (selectedType === "ALL" || rowValue === selectedType) {
                row.style.display = "";
            } else {
                row.style.display = "none";
            }
        }
    })
})