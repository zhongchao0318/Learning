$(function () {
    let x, y;
    let index = 0;
    let screenSizeWidth = $("body").width();
    let screenSizeHeight = $("body").height();
    let halfvmin = (screenSizeWidth > screenSizeHeight ? screenSizeHeight / 2 : screenSizeWidth / 2) * 0.8;

    console.log('halfvmin', halfvmin);

    $(document).on("click", function (e) {
        x = e.pageX;
        y = e.pageY;
        waveMove(x, y, index++);
    });

    function waveMove(x, y, z) {
        $(".g-container").append(`
            <div class="g-position g-position${z}" style="top:${y - halfvmin}px; left:${x - halfvmin}px; z-index:${z}">
                <div class="g-center">
                    <div class="wave g-wave1"></div>
                    <div class="wave g-wave2"></div>
                    <div class="wave g-wave3"></div>
                    <div class="wave g-wave4"></div>
                </div>
            </div>
        `);

        setTimeout(function () {
            $(`.g-position${z}`).remove();
        }, 30000);
    }
});
