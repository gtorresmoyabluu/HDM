jQuery.fn.visible = function () {
    return this.css('visibility', 'visible');
};

jQuery.fn.invisible = function () {
    return this.css('visibility', 'hidden');
};

jQuery.fn.visibilityToggle = function () {
    return this.css('visibility', function (i, visibility) {
	return (visibility === 'visible') ? 'hidden' : 'visible';
    });
};

function changeCollapsibleIndicator(element) {
    if (element.html() === 'keyboard_arrow_right')
	element.html('keyboard_arrow_down');
    else
	element.html('keyboard_arrow_right');
}