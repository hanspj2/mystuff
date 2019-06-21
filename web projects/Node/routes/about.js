var express = require('express');
var router = express.Router();
const aboutController = require('../controllers/about');

router.get('/', aboutController.index);

module.exports = router;
