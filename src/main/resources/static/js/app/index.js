let main = {
    init: function () {
        let _this = this;

        $('.btn-read').on('click', function () {
            _this.read();
        });

        $('.btn-delete').on('click', function () {
            _this.delete();
        });

        $('.btn-cancel').on('click', function (){
            _this.cancel();
        });
    },
    read: function () {
        let id = $('.post-id').val();

        $.ajax({
            method: 'GET',
            url: '/posts/post/' + id
        }).done(function () {
            window.location.href = '/posts/post/' + id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        let id = $('.post-id').val();

        $.ajax({
            method: 'DELETE',
            url: '/posts/post/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            window.location.href = '/posts';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    cancel: function () {
        $.ajax({
            method: 'GET',
            url: '/posts'
        }).done(function (){
            window.location.href = this.url;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();