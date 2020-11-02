function SpeedController(id, controller) {
    this._dom = document.getElementById(id);
    this._controller = controller;
    this._wrapper = null;
    this._inner = null;
    this._name = parseInt(this._dom.getAttribute('data-name'));
    this._max = parseInt(this._dom.getAttribute('data-max'));
    this._init();
}

var CONTROLLER_WIDTH = 500;
SpeedController.prototype = {
    _init: function () {
        this._wrapper = this._dom.getElementsByClassName('wrapper')[0];
        this._inner = this._wrapper.getElementsByClassName('inner')[0];
        this._valueP = this._dom.getElementsByClassName('value')[0];
        var _this = this;
        this._wrapper.onclick = function (e) {
            var offsetX = e.offsetX;
            _this.showAndSetSpeed(offsetX)
        };
        var stopBtn = this._dom.getElementsByClassName('btn-stop')[0];
        stopBtn.onclick = function () {
            _this.showAndSetSpeed(CONTROLLER_WIDTH / 2);
        };
    },
    showAndSetSpeed: function (offset) {
        var value = offset - (CONTROLLER_WIDTH / 2);
        value = this.calcPwm(value);
        this._inner.style.left = offset + 'px';
        this._valueP.innerText = value;
        this.setSpeed(value);
    },
    calcPwm: function (value) {
        var max = this._max;
        return max * value / (CONTROLLER_WIDTH / 2);
    },
    setSpeed: function (value) {
        var msg = {
            action: 'set_speed',
            speed: value,
            motor: this._name
        };
        this._controller.sendMessage(JSON.stringify(msg));
    }
};

function RobotController() {
    this._ws = null;
}

RobotController.prototype = {
    connect: function () {
        var host = location.host;
        this._ws = new WebSocket('ws://' + host + '/ws/robot-controller');
        var _this = this;
        this._ws.onopen = function (e) {
            _this.onConnected(e);
        };
        this._ws.onclose = function (e) {
            _this.onDisconnected(e);
        };
        this._ws.onerror = function (e) {
            _this.onConnectError(e);
        }
    },
    onConnected: function (e) {
        console.log('connected', e);
    },
    onDisconnected: function (e) {
        console.log('Disconnected', e);
    },
    onConnectError: function (e) {
        console.log('connect error', e);
    },
    sendMessage: function (msg) {
        this._ws.send(msg);
    },
    start: function () {
        this._enable(true);
    },
    stop: function () {
        this._enable(false);
    },
    _enable: function (state) {
        var msg = {
            action: 'enable',
            state: state
        };
        this.sendMessage(JSON.stringify(msg));
    }
};
var onReady = function () {
    var controller = new RobotController();
    controller.connect();
    var scFl = new SpeedController('sc-fl', controller);
    var scFr = new SpeedController('sc-fr', controller);
    var scBl = new SpeedController('sc-bl', controller);
    var scBr = new SpeedController('sc-br', controller);
    console.log('controller:', scFl, scFr, scBl, scBr);
    document.getElementById('btn-start').onclick = function () {
        controller.start();
    };
    document.getElementById('btn-stop').onclick = function () {
        controller.stop();
    };
};

window.onload = onReady;