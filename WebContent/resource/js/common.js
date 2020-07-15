function MobileSideBar__init() {
    var $btnToggleMobilesideBar = $('.btn-toggle-mobile-side-bar');

    $btnToggleMobilesideBar.click(function() {
        console.log($(this).hasClass('active'))
        if ($(this).hasClass('active')) {
            $(this).removeClass('active');
            $('.mobile-side-bar').removeClass('active');
        } else {
            $(this).addClass('active');
            $('.mobile-side-bar').addClass('active');
        }
    });
}



$(function() {
    MobileSideBar__init();

})