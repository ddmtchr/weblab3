let canvas = document.getElementById('graph-canvas')
let ctx = canvas.getContext('2d')
let halfWidth = canvas.width / 2
let halfHeight = canvas.height / 2
let rDefault = (canvas.width * 0.5) - 30 - (((canvas.width * 0.5) - 30) % 30)
let canvasFont = '16px sans-serif'
ctx.translate(halfWidth, halfHeight)
ctx.scale(1, -1)

function drawPoint(x, y, r, hit) {
    const scaledX = x * rDefault / 3
    const scaledY = y * rDefault / 3
    console.log(scaledX, scaledY, hit)
    ctx.fillStyle = hit ? '#080' : '#b00'
    ctx.beginPath()
    ctx.moveTo(scaledX, scaledY)
    ctx.arc(scaledX, scaledY, 2.5, 0, 2 * Math.PI, true)
    ctx.closePath()
    ctx.fill()
}

function drawGraph(currentR) {
    ctx.clearRect(-halfWidth, -halfHeight, canvas.width, canvas.height)
    drawAxis()
    drawShapes(currentR)
}

function drawShapes(currentR) {
    const r = rDefault / 3 * currentR
    let x1 = -r / 2
    let y1 = 0
    let x2 = 0
    let y2 = 0
    let x3 = 0
    let y3 = -r
    ctx.fillStyle = getComputedStyle(document.body).getPropertyValue('--canvas-shapes-color')

    drawTriangle(x1, y1, x2, y2, x3, y3)
    drawCircleArc(0, 0, r / 2, -Math.PI / 2, 0, false)
    drawRectangle(-r / 2, r, r / 2, -r)
}

function drawTriangle(x1, y1, x2, y2, x3, y3) {
    ctx.beginPath()
    ctx.moveTo(x1, y1)
    ctx.lineTo(x2, y2)
    ctx.lineTo(x3, y3)
    ctx.closePath()
    ctx.fill()
}

function drawCircleArc(centerX, centerY, r, startAngle, endAngle, counterclockwise) {
    ctx.beginPath()
    ctx.moveTo(centerX, centerY)
    ctx.arc(centerX, centerY, r, startAngle, endAngle, counterclockwise)
    ctx.closePath()
    ctx.fill()
}

function drawRectangle(x, y, width, height) {
    ctx.beginPath()
    ctx.rect(x, y, width, height)
    ctx.closePath()
    ctx.fill()
}

function drawAxis() {
    ctx.fillStyle = getComputedStyle(document.body).getPropertyValue('--canvas-axis-color')
    ctx.strokeStyle = getComputedStyle(document.body).getPropertyValue('--canvas-axis-color')
    ctx.font = canvasFont

    drawHorizontalAxis()
    drawVerticalAxis()

    drawVerticalStroke(-rDefault, 0, '-3')
    drawVerticalStroke(-rDefault / 3 * 2, 0, '-2')
    drawVerticalStroke(-rDefault / 3, 0, '-1')
    drawVerticalStroke(rDefault / 3, 0, '1')
    drawVerticalStroke(rDefault / 3 * 2, 0, '2')
    drawVerticalStroke(rDefault, 0, '3')

    drawHorizontalStroke(0, -rDefault, '-3')
    drawHorizontalStroke(0, -rDefault / 3 * 2, '-2')
    drawHorizontalStroke(0, -rDefault / 3, '-1')
    drawHorizontalStroke(0, rDefault / 3, '1')
    drawHorizontalStroke(0, rDefault / 3 * 2, '2')
    drawHorizontalStroke(0, rDefault, '3')
}

function drawVerticalAxis() {
    ctx.beginPath()
    ctx.moveTo(0, -halfHeight)
    ctx.lineTo(0, halfHeight)
    ctx.lineTo(-3, halfHeight - 10)
    ctx.lineTo(3, halfHeight - 10)
    ctx.lineTo(0, halfHeight)
    ctx.stroke()
    ctx.fill()
    ctx.scale(1, -1)
    ctx.textAlign = 'left'
    ctx.fillText('Y', -17, -halfHeight + 14)
    ctx.scale(1, -1)
}

function drawHorizontalAxis() {
    ctx.beginPath()
    ctx.moveTo(-halfWidth, 0)
    ctx.lineTo(halfWidth, 0)
    ctx.lineTo(halfWidth - 10, 3)
    ctx.lineTo(halfWidth - 10, -3)
    ctx.lineTo(halfWidth, 0)
    ctx.stroke()
    ctx.fill()
    ctx.scale(1, -1)
    ctx.textAlign = 'left'
    ctx.fillText('X', halfWidth - 12, 20)
    ctx.scale(1, -1)
}

function drawVerticalStroke(x, y, text) {
    ctx.beginPath()
    ctx.moveTo(x, y + 3)
    ctx.lineTo(x, y - 3)
    ctx.stroke()
    ctx.scale(1, -1)
    ctx.textAlign = 'center'
    ctx.fillText(text, x, y + 20)
    ctx.scale(1, -1)
}

function drawHorizontalStroke(x, y, text) {
    ctx.beginPath()
    ctx.moveTo(x + 3, y)
    ctx.lineTo(x - 3, y)
    ctx.stroke()
    ctx.scale(1, -1)
    ctx.textAlign = 'right'
    ctx.fillText(text, x - 5, -y + 5)
    ctx.scale(1, -1)
}

function scaleX(x) {
    const canvasEdgeX = Math.round(canvas.getBoundingClientRect().left)
    return +(((x - canvasEdgeX - halfWidth) * 3 / rDefault).toFixed(2))
}

function scaleY(y) {
    const canvasEdgeY = Math.round(canvas.getBoundingClientRect().top)
    return +((-(y - canvasEdgeY - halfHeight) * 3 / rDefault).toFixed(2))
}
