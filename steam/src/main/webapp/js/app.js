/*
 * Copyright (c) 2012-2015 Shailendra Singh <shailendra_01@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
var steam = {
	tiles: [],
		
	renderMarkdown: function(tileId) {
		marked.setOptions({
			highlight: function (code) {
				return hljs.highlightAuto(code).value;
			}
	    });
		
		var tile = $(getIdWithPrefix(tileId));
		tile.find('.pseudo-rendered-content').html(marked(tile.find('.pseudo-db-content').text()));
	}	
};

$(function() {
	for(var i=0; i < steam.tiles.length; i++) {
		var tile = $(getIdWithPrefix(steam.tiles[i]));
		
		tile.find('.pseudo-edit-btn').click(function() {
			$(this).hide();
			var parentTile = $(this).closest('.tile');
			parentTile.find('.pseudo-new-content > textarea').val(parentTile.find('.pseudo-db-content').text());
			parentTile.find('.pseudo-rendered-content').hide();
			parentTile.find('.pseudo-new-content').show();
		});
		
		tile.find('.pseudo-cancel-btn').click(function() {
			var parentTile = $(this).closest('.tile');
			parentTile.find('.pseudo-new-content').hide();
			parentTile.find('.pseudo-rendered-content').show();
			tile.find('.pseudo-edit-btn').show();
		});
	}
});