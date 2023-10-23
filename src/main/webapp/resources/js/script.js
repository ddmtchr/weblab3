const yField = document.querySelector('.y-input')

canvas.addEventListener('click', function (event) {
    const scaledX = scaleX(event.x)
    const scaledY = scaleY(event.y)
    document.getElementById('graph-form:graph-x').value = scaledX
    document.getElementById('graph-form:graph-y').value = scaledY
    addByClick();
})

yField.addEventListener('input', function () {
    const input = yField.value
    if (isNumeric(input) || input.trim() === '') {
        if (-3 <= input && input <= 5) {
            yField.classList.remove('invalid')
        } else {
            yField.classList.add('invalid')
        }
    } else {
        yField.classList.add('invalid')
    }
})

function isNumeric(str) {
    return /^\s*[+-]?([0-9]*[.])?[0-9]+\s*$/.test(str)
}
