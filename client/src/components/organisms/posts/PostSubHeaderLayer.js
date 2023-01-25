import styled from 'styled-components';
import { useEffect, useState } from 'react';
import { LabelListTitle } from '../../../styles/typo';
import PostIndexLIstRender from '../../molecules/posts/PostIndexLIstRender';
import { BlueShadowButton } from '../../atoms/Buttons';

const Container = styled.div`
  box-sizing: border-box;
  display: flex;
  width: calc(100% / 12 * 10);
  height: fit-content;
  margin: 0 auto;
  justify-content: space-between;
  align-items: center;
  padding: 5px 31px;
  gap: 10px;
  margin-bottom: 5vh;
`;

const IndexLabelBox = styled.div`
  display: flex;
  flex-direction: column;
  ${LabelListTitle}
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
      htmlTag: 'h2',
      content: '제목2',
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

const PostSubHeaderLayer = ({ indexLabel }) => {
  const [Labels, SetILabels] = useState({ indexLabel });
  useEffect(() => {
    SetILabels(INDEXLABEL.data);
  });
  return (
    <Container>
      <IndexLabelBox>
        Index
        {Labels && Labels.length > 0 ? Labels.map(anItem => <PostIndexLIstRender key={anItem.id} item={anItem} />) : ''}
      </IndexLabelBox>
      <BlueShadowButton> Add</BlueShadowButton>
    </Container>
  );
};

export default PostSubHeaderLayer;
