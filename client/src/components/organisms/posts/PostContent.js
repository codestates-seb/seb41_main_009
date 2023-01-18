import styled from 'styled-components';
import { useEffect, useState } from 'react';
import PostContentRender from '../../molecules/posts/PostContentRender';

const Container = styled.div`
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  width: calc(100%);
  height: fit-content;
  margin: 0 auto;
  justify-content: space-between;
  align-items: center;
  gap: 10px;
  margin-bottom: 5vh;
`;

const INDEXLABEL = {
  data: [
    {
      id: 1,
      htmlTag: 'h1',
      content: '제목1',
      image: false,
    },
    {
      id: 2,
      htmlTag: 'p',
      content:
        'Vestibulum eu quam nec neque pellentesque efficitur id eget nisl. Proin porta est convallis lacus blandit pretium sed non enim. Maecenas lacinia non orci at aliquam. Donec finibus, urna bibendum ultricies laoreet, augue eros luctus sapien, ut euismod leo tortor ac enim. In hac habitasse platea dictumst. Sed cursus venenatis tellus, non lobortis diam volutpat sit amet. Sed tellus augue, hendrerit eu rutrum in, porttitor at metus. Mauris ac hendrerit metus. Phasellus mattis lectus commodo felis egestas, id accumsan justo ultrices. Phasellus aliquet, sem a placerat dapibus, enim purus dictum lacus, nec ultrices ante dui ac ante. Phasellus placerat, urna.Vestibulum eu quam nec neque pellentesque efficitur id eget nisl. Proin porta est convallis lacus blandit pretium sed non enim. Maecenas lacinia non orci at aliquam. Donec finibus, urna bibendum ultricies laoreet, augue eros luctus sapien, ut euismod leo tortor ac enim. In hac habitasse platea dictumst. Sed cursus venenatis tellus, non lobortis diam volutpat sit amet. Sed tellus augue, hendrerit eu rutrum in, porttitor at metus. Mauris ac hendrerit metus. Phasellus mattis lectus commodo felis egestas, id accumsan justo ultrices. Phasellus aliquet, sem a placerat dapibus, enim purus dictum lacus, nec ultrices ante dui ac ante. Phasellus placerat, urna.Vestibulum eu quam nec neque pellentesque efficitur id eget nisl. Proin porta est convallis lacus blandit pretium sed non enim. Maecenas lacinia non orci at aliquam. Donec finibus, urna bibendum ultricies laoreet, augue eros luctus sapien, ut euismod leo tortor ac enim. In hac habitasse platea dictumst. Sed cursus venenatis tellus, non lobortis diam volutpat sit amet. Sed tellus augue, hendrerit eu rutrum in, porttitor at metus. Mauris ac hendrerit metus. Phasellus mattis lectus commodo felis egestas, id accumsan justo ultrices. Phasellus aliquet, sem a placerat dapibus, enim purus dictum lacus, nec ultrices ante dui ac ante. Phasellus placerat, urna.',
      image: true,
    },
    {
      id: 3,
      htmlTag: 'h3',
      content: '제목3',
      image: false,
    },
    {
      id: 4,
      htmlTag: 'h1',
      content: '제목4',
      image: false,
    },
    {
      id: 5,
      htmlTag: 'h1',
      content: '제목5',
      image: true,
    },
  ],
};

const PostSubHeaderContainer = ({ indexLabel }) => {
  const [Labels, SetILabels] = useState({ indexLabel });
  useEffect(() => {
    SetILabels(INDEXLABEL.data);
  });
  return (
    <Container>
      {Labels && Labels.length > 0 ? Labels.map(anItem => <PostContentRender key={anItem.id} item={anItem} />) : ''}
    </Container>
  );
};

export default PostSubHeaderContainer;
