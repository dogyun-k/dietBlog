let board = {

    init: function () {
        let _this = this;

        $('#btn-create-board').on('click', function () {
            _this.create();
        });

        $('#btn-updateView-board').on('click', function () {
            _this.updateView();
        });

        $('#btn-update-board').on('click', function () {
            _this.update();
        })

        $('#btn-delete-board').on('click', function () {
            _this.delete();
        });

        $('#btn-cancel-board').on('click', function () {
            _this.cancel();
        });
    },
    create: function () {
        let data = {
            'title': $('#title').val(),
            'content': $('#content').val()
        }

        $.ajax({
            method: 'POST',
            url: '/board/new',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            // alert('등록되었습니다.');
            window.location.href = '/board';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    updateView: function () {
        let id = $('#board-id').val();

        $.ajax({
            method: 'GET',
            url: '/board/update/' + id
        }).done(function () {
            window.location.href = this.url;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    update: function (){
        let id = $('#board-id').val();
        let data = {
            'title': $('#title').val(),
            'content': $('#content').val()
        }

        $.ajax({
            method: 'POST',
            url: '/board/update/' + id,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('수정되었습니다.');
            window.location.href = '/board';
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    },
    delete: function () {
        let id = $('#board-id').val();
        let data = {
            'id': id
        };

        $.ajax({
            method: 'POST',
            url: '/board/delete',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            window.location.href = '/board';
            // alert('삭제되었습니다.');
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    cancel: function () {
        $.ajax({
            method: 'GET',
            url: '/board'
        }).done(function () {
            window.location.href = this.url;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

board.init();